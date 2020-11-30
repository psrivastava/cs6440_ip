import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import LeftDrawer from "./LeftDrawer";
import "../css/ReportPage.css";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { Grid, Paper } from "@material-ui/core";
//import "../util/get-data-test";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

const { default: axios } = require("axios");

const drawerWidth = 240;
const myStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
  },
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(3),
  },

  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: `${drawerWidth}`,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
}));

const URL = "https://jsonplaceholder.typicode.com/users";

var highrisk_medicine = [
  "Azithromycin",
  "Vicodin",
  "oxycodone",
  "hydrocodone",
  "OxyContin",
  "Percocet",
  "oxymorphone",
  "Opana",
  "morphine",
  "Kadian",
  "Avinza",
  "codeine",
  "fentanyl",
];
var lowrisk_medicine = ["Warfarin", "Atropine", "Verapamil"];

export function ReportPage() {
  const classes = myStyles();
  const [patients, setPatients] = React.useState([]);
  const [searchBtnTxt, setSearchBtnTxt] = React.useState("Search");
  const [stopBtnTxt, setStopBtnTxt] = React.useState("Cancel");
  const [searchTxt, setSearchTxt] = React.useState("");
  const stopFlag = React.useRef(false);

  var patientInfo = [];

  React.useEffect(() => {}, []);

  const sayHello = () => {
    //alert('Hello!');
    stopFlag.current = false;
    patientInfo = [];
    getData();
  };

  const stopSearch = () => {
    stopFlag.current = true;
    setStopBtnTxt("Cancel");
  };

  function removeObservation(pid, obserId, btnEvt) {

    fetch(`http://hapi.fhir.org/baseR4/Observation/${obserId}?_pretty=true`, {
      headers: {
        Accept: "application/fhir+json;q=1.0, application/json+fhir;q=0.9",
        "Content-Type": "application/fhir+json; charset=UTF-8",
      },
      method: "delete",
    })
      .then(function (response) {
        console.log(response.status);
        return response.json();
      })
      .then(function (data) {
        console.log("Deleted observation:", obserId);
        patients.find((p) => p.id === pid).buttonText = "Report";
        setPatients(patients);
      });
  }

  function addNewObservation(pid, btnEvt) {
    var postBody = {
      resourceType: "Observation",
      status: "final",
      code: {
        coding: [
          {
            system: "http://loinc.org",
            code: "70004-7",
            display: "Diagnostic study note",
          },
        ],
        text: "Diagnostic study note",
      },
      subject: {
        reference: `Patient/${pid}`,
      },
      effectiveDateTime: new Date().toISOString(),
      issued: new Date().toISOString(),
      valueString: "HIGH_RISK",
    };

    fetch("http://hapi.fhir.org/baseR4/Observation?_format=json&_pretty=true", {
      headers: {
        Accept: "application/fhir+json;q=1.0, application/json+fhir;q=0.9",
        "Content-Type": "application/fhir+json; charset=UTF-8",
      },
      method: "post",
      body: JSON.stringify(postBody),
    })
      .then(function (response) {
        console.log(response.status);
        return response.json();
      })
      .then(function (data) {
        console.log("Created observation:", data.id);
        patients.find((p) => p.id === pid).buttonText = "-Redact";
        setPatients(patients);
      });
  }

  async function getMedicationName(patientId, patientName, patientDOB) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    var medicationQuery = `http://hapi.fhir.org/baseR4/MedicationRequest?_pretty=true&patient=${patientId}&_format=json`;

    let response = await fetch(medicationQuery);
    let data = await response.json();

    if (typeof data.entry == "undefined") return;

    var highRisk = false;
    var highrisk_medname = "";
    var lowRisk = false;
    var lowrisk_medname = "";
    data.entry.forEach((med) => {
      let medname = med.resource.medicationCodeableConcept?.text
        ? med.resource.medicationCodeableConcept.text
        : "";
      //console.log(medname);
      highrisk_medicine.forEach((m1) => {
        if (medname.indexOf(m1) > -1) {
          highRisk = true;
          highrisk_medname = medname;
        }
      });

      lowrisk_medicine.forEach((m1) => {
        if (medname.indexOf(m1) > -1) {
          lowRisk = true;
          lowrisk_medname = medname;
        }
      });
    });

    let observationResponse = await fetch(
      `http://hapi.fhir.org/baseR4/Observation?patient=${patientId}&code=http://loinc.org|70004-7&_pretty=true`
    );
    let obserdata = await observationResponse.json();
    let alreadyReported = obserdata.total > 0;
    var observationId = "nul";
    if (obserdata.total > 0) observationId = obserdata.entry[0].resource.id;
    let buttonText = alreadyReported ? "-Redact" : "Report";

    if (highRisk || lowRisk) {
      let pinfo = {
        id: patientId,
        name: patientName,
        medname: highRisk ? `${highrisk_medname}` : `${lowrisk_medname}`,
        status: highRisk ? "HIGH_RISK" : "LOW_RISK",
        buttonText: buttonText
      };

      if (alreadyReported)
        pinfo.obserId = obserdata.entry[0].resource.id;

      patientInfo.push(pinfo);
    }
  }

  function updateSummaryCard(data) {
    var promises = [];
    if (typeof data.entry != "undefined") {
      data.entry.forEach(async (element) => {
        await getMedicationName(
          element.resource.id,
          element.resource.name[0].given +
            " " +
            element.resource.name[0].family,
          element.resource.birthDate
        );
        //promises.push();
      });
    }
    //await Promise.all(promises)
  }

  const getData = async (startUrl) => {
    if (stopFlag.current === true) return;

    setSearchBtnTxt(" . . . ");

    let URL = "";
    const options = {
      headers: {
        Accept: "application/fhir+json;q=1.0, application/json+fhir;q=0.9",
        "Content-Type": "application/fhir+json; charset=UTF-8",
      },
    };

    if (typeof startUrl == "undefined") {
      const queryStr = searchTxt.split(":")[1];
      if (searchTxt.split(":")[0].toLowerCase() === "state") {
        URL = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&address-state=${queryStr}&_format=json`;
      } else if (searchTxt.split(":")[0].toLowerCase() === "zip") {
        URL = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&address-postalcode=${queryStr}&_format=json`;
      } else if (searchTxt.split(":")[0].toLowerCase() === "name") {
        URL = `http://hapi.fhir.org/baseR4/Patient?_pretty=true&address-country=US&name=${queryStr}&_format=json`;
      }
    } else {
      URL = startUrl;
    }

    const response = await axios.get(URL, options);

    updateSummaryCard(response.data);
    setSearchBtnTxt(" Search ");

    setPatients(patientInfo);

    if (response.data.link.length > 1) {
      await getData(response.data.link[1].url);
    }
  };

  const removeData = (row, btnEvent) => {
    console.log(row);
    if (row.buttonText === "Report") {
      // add
      addNewObservation(row.id, btnEvent)
    } else {
      // remove
      removeObservation(row.id, row.obserId, btnEvent);
    }
  };

  const renderHeader = () => {
    let headerElement = [
      "Id",
      "Name",
      "Medication",
      "Risk Category",
      "Report to care Provider",
    ];

    return headerElement.map((key, index) => {
      return (
        <th key={index} hidden={index === 0}>
          {" "}
          {key.toUpperCase()}{" "}
        </th>
      );
    });
  };

  const renderBody = () => {
    return (
      patients &&
      patients.map((p) => {
        return (
          <tr key={p.id}>
            <td hidden={true}> {p.id} </td> <td> {p.name} </td>{" "}
            <td> {p.medname} </td>
            <td> {p.status} </td>
            <td className="opration">
              <Button className="button" onClick={(e) => removeData(p, e)}>
                {p.buttonText}
              </Button>
            </td>
          </tr>
        );
      })
    );
  };

  return (
    <div className={classes.root}>
      <LeftDrawer />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap>
            Report
          </Typography>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <>
          <Grid container direction="column" xs={12} sm={12}>
            <Grid item>
              <form noValidate autoComplete="off">
                <TextField
                  id="outlined-basic"
                  label="Outlined"
                  variant="outlined"
                  placeholder="zip:98 or state:WA or name:abc"
                  onChange={(e) => setSearchTxt(e.target.value)}
                />
                <Button
                  style={{
                    maxWidth: "120px",
                    maxHeight: "50px",
                    minWidth: "120px",
                    minHeight: "50px",
                  }}
                  variant="contained"
                  id="searchBtn"
                  onClick={sayHello}
                >
                  {searchBtnTxt}
                </Button>
                <Button
                  style={{
                    maxWidth: "120px",
                    maxHeight: "50px",
                    minWidth: "120px",
                    minHeight: "50px",
                  }}
                  variant="contained"
                  id="stopSearch"
                  onClick={stopSearch}
                >
                  {stopBtnTxt}
                </Button>
              </form>
            </Grid>
            <Grid item>
              <table id="employee">
                <thead>
                  <tr> {renderHeader()} </tr>
                </thead>
                <tbody> {renderBody()} </tbody>
              </table>
            </Grid>
          </Grid>
        </>
      </main>
    </div>
  );
}
