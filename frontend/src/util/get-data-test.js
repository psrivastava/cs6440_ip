//adapted from the cerner smart on fhir guide. updated to utalize client.js v2 library and FHIR R4

async function getMedicationName(patientId, patientName, patientDOB) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    var medicationQuery = `http://hapi.fhir.org/baseR4/MedicationRequest?_pretty=true&patient=${patientId}&_format=json`;

    let response = await fetch(medicationQuery);
    let data = await response.json();

    if (typeof data.entry == 'undefined')
        return;

    var highRisk = false;
    var highrisk_medname = "";
    var lowRisk = false;
    var lowrisk_medname = "";
    data.entry.forEach(med => {
        let medname = med.resource.medicationCodeableConcept?.text ? med.resource.medicationCodeableConcept.text : "";
        //console.log(medname);
        highrisk_medicine.forEach(m1 => {
            if (medname.indexOf(m1) > -1) {
                highRisk = true;
                highrisk_medname = medname;
            }
        });

        lowrisk_medicine.forEach(m1 => {
            if (medname.indexOf(m1) > -1) {
                lowRisk = true;
                lowrisk_medname = medname;
            }
        });
    });

    let observationResponse = await fetch(`http://hapi.fhir.org/baseR4/Observation?patient=${patientId}&code=http://loinc.org|70004-7&_pretty=true`);
    let obserdata = await observationResponse.json();
    let alreadyReported = (obserdata.total > 0);
    var observationId = "nul";
    if (obserdata.total > 0)
        observationId = obserdata.entry[0].resource.id;
    let buttonText = alreadyReported ? "-Redact" : "Report";

    // https://stackoverflow.com/questions/14460421/get-the-contents-of-a-table-row-with-a-button-click
    let celltext = getPatientCellText(patientId, patientName, observationId);

    if (highRisk) {
        resut_list.innerHTML += `<tr><td hidden=true class="rowId">${patientId}</td><td class="pInfo">${celltext}</td>` +
        `<td>${highrisk_medname}</td><td>HIGH</td><td><button data-obserid=${observationId} onclick='reportBtnClickEvnt(this)'>${buttonText}</button></td></tr>`;
        incrementDrugAbusePatientCount();
    } else if (lowRisk) {
        resut_list.innerHTML += `<tr><td hidden=true class="rowId">${patientId}</td><td class="pInfo">${celltext}</td>` +
        `<td>${lowrisk_medname}</td><td>LOW</td><td><button data-obserid=${observationId} onclick='reportBtnClickEvnt(this)'>${buttonText}</button></td></tr>`;
        incrementDrugAbusePatientCount();
    } else if (alreadyReported) {
        resut_list.innerHTML += `<tr><td hidden=true class="rowId">${patientId}</td><td class="pInfo">${celltext}</td>` +
        `<td></td><td>HIGH</td><td><button data-obserid=${observationId} onclick='reportBtnClickEvnt(this)'>${buttonText}</button></td></tr>`;
        incrementDrugAbusePatientCount();
    }
}

function getPatientCellText(patientId, patientName, observationId) {
    var celltext = "";
    if (observationId !== 'nul') {
        celltext = `<a href="#" onClick="MyWindow=window.open('http://hapi.fhir.org/baseR4/Observation?patient=${patientId}` +
        `&_include=Observation:patient&_pretty=true','MyWindow','width=1000,height=500'); return false;">${patientName}</a>`;
    } else {
        celltext = `<a href="#" onClick="MyWindow=window.open('http://hapi.fhir.org/baseR4/Patient/${patientId}?_pretty=true',` +
            `'MyWindow','width=1000,height=500'); return false;">${patientName}</a>`;
    }

    return celltext;
}

$(".report-btn").click(function() {
    var $item = $(this).closest("tr")   // Finds the closest row <tr>
                       .find(".rowId")     // Gets a descendent with class="nr"
                       .text();         // Retrieves the text within <td>

    alert($item);    // Outputs the answer
});

function reportBtnClickEvnt(btnEvt) {
    let pid = $(btnEvt).closest("tr")
                .find(".rowId")     // Gets a descendent with class="nr"
                .text();
    console.log("PatientId:", pid);
    //alert("clicked " + pid);
    let obserid = btnEvt.getAttribute("data-obserid")
    if ( btnEvt.innerText === '-Redact') {
        removeObservation(pid, obserid, btnEvt);
    } else {
        addNewObservation(pid, btnEvt);
    }
}

function removeObservation(pid, obserId, btnEvt) {
    let name = $(btnEvt).closest("tr")
                .find(".pInfo")
                .text();

    fetch(`http://hapi.fhir.org/baseR4/Observation/${obserId}?_pretty=true`, {
        headers: {
            'Accept': 'application/fhir+json;q=1.0, application/json+fhir;q=0.9',
            'Content-Type': 'application/fhir+json; charset=UTF-8'
        },
        method: 'delete'
    }).then(function (response) {
        console.log(response.status);
        return response.json();
    }).then(function (data) {
        console.log('Deleted observation:', obserId);
        btnEvt.innerText = "Report";
        btnEvt.setAttribute("data-obserid", "nul");
        updateCellText(btnEvt, pid, name, 'nul');
    });
}

function addNewObservation(pid, btnEvt) {
    var postBody = {
        resourceType: "Observation",
        status: "final",
        code: {
            coding: [{
                system: "http://loinc.org",
                code: "70004-7",
                display: "Diagnostic study note"
            }],
            text: "Diagnostic study note"
        },
        subject: {
            reference: `Patient/${pid}`
        },
        effectiveDateTime: new Date().toISOString(),
        issued: new Date().toISOString(),
        valueString: "HIGH_RISK"
    };

    let name = $(btnEvt).closest("tr")
                .find(".pInfo")
                .text();

    fetch('http://hapi.fhir.org/baseR4/Observation?_format=json&_pretty=true', {
        headers: {
            'Accept': 'application/fhir+json;q=1.0, application/json+fhir;q=0.9',
            'Content-Type': 'application/fhir+json; charset=UTF-8'
        },
        method: 'post',
        body: JSON.stringify(postBody)
    }).then(function (response) {
        console.log(response.status);
        return response.json();
    }).then(function (data) {
        console.log('Created observation:', data.id);
        btnEvt.innerText = "-Redact";
        btnEvt.setAttribute("data-obserid", data.id);
        updateCellText(btnEvt, pid, name, data.id);
    });
}

function incrementDrugAbusePatientCount() {
    var currentVal = document.getElementById('dapc').innerText;
    let newVal = Number(currentVal) + 1;
    document.getElementById("dapc").innerText = newVal;
}

function updateCellText(btnEvt, patientId, patientName, observationId) {
    let cellText = $(btnEvt).closest("tr")
                .find(".pInfo")[0];

    cellText.innerHTML = getPatientCellText(patientId, patientName, observationId);
}

async function updateSummaryCard(data) {
    var currentVal = document.getElementById('tpc').innerText;
    let newVal = Number(currentVal) + (data.entry ? data.entry.length : 0);
    document.getElementById("tpc").innerText = newVal;

    var promises = [];
    if (typeof data.entry != 'undefined') {
        data.entry.forEach(element => {
            promises.push(getMedicationName(element.resource.id, element.resource.name[0].given + " " +
                element.resource.name[0].family, element.resource.birthDate));
        });
    }
    await Promise.all(promises)
}

async function generateReport() {
    document.getElementById('searchBtn').innerText = " . . . ";
    stopFlag = false;
    await generateReportCore();
    document.getElementById('searchBtn').innerText = "Search";
}

async function generateReportCore(startUrl) {
    if (stopFlag)
        return;

    // get value
    let queryExp = document.getElementById('searchStrBox').value;
    if (typeof queryExp == 'undefined' || queryExp == '') {
        return;
    }

    let queryUrl = "";
    if (typeof startUrl == 'undefined') {
        // reset
        document.getElementById('tpc').innerText = "0";
        document.getElementById('dapc').innerText = "0";
        resut_list.innerHTML = "<tr><td hidden=true></td><td></td><td></td><td></td><td></td></tr>";

        let queryStr = queryExp.split(":")[1];

        if (queryExp.split(":")[0].toLowerCase() == "state") {
            queryUrl = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&address-state=${queryStr}&_format=json`;
        } else if (queryExp.split(":")[0].toLowerCase() == "zip") {
            queryUrl = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&address-postalcode=${queryStr}&_format=json`;
        } else if (queryExp.split(":")[0].toLowerCase() == "name") {
            queryUrl = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&name=${queryStr}&_format=json`;
        }
    } else {
        queryUrl = startUrl;
    }

    let response = await fetch(queryUrl);
    let data = await response.json();

    await updateSummaryCard(data);

    if (data.link.length > 1) {
        await generateReportCore(data.link[1].url);
    }

}

var stopFlag = false;
function stopTheSearch() {
    document.getElementById('searchBtn').innerText = "Search";
    stopFlag = true;
}

var highrisk_medicine = ["Azithromycin", "Vicodin", "oxycodone", "hydrocodone",
                        "OxyContin", "Percocet", "oxymorphone", "Opana",
                        "morphine", "Kadian", "Avinza", "codeine", "fentanyl"];
var lowrisk_medicine = ["Warfarin", "Atropine", "Verapamil"];

//event listner when the add button is clicked to call the function that will add the note to the weight observation
document.getElementById('searchBtn').addEventListener('click', generateReport);
document.getElementById('stopSearch').addEventListener('click', stopTheSearch);

