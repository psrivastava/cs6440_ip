import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import LeftDrawer from "./LeftDrawer";
import Checkbox from "@material-ui/core/Checkbox";
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import { Grid, Paper } from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

const drawerWidth = 240;
const myStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
  },
  rightside: {
    display: "flex",
    flexDirection: "row",
    alignItems: "flex-start",
  },
  content: {
    flexGrow: 1,
    width: "1200px",
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(2),
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: `${drawerWidth}`,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
  paper: {
    width: "100%",
    textAlign: 'left',
    justifyContent: 'center',
    alignContent: 'center',
    padding: '5px'
  },
}));

const handleChange = () => {};

const doSomething = async () => {};

export default function HomePage() {
  const classes = myStyles();

  const [medicines, setMedicines] = React.useState([]);
  const [appts, setAppts] = React.useState([]);
  const [vitStats, setVitalStats] = React.useState([]);
  const [searchBtnTxt, setSearchBtnTxt] = React.useState("Search");

  async function getMedicationName(patientId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const medicationQuery = `http://hapi.fhir.org/baseR4/MedicationRequest?_pretty=true&patient=${patientId}&_format=json`;

    let response = await fetch(medicationQuery);
    let data = await response.json();

    if (typeof data.entry == "undefined") return;

    var med_names = [];
    data.entry.forEach((med) => {
      let medname = med.resource.medicationReference?.display
        ? med.resource.medicationReference.display
        : med.resource.medicationReference.reference;
      med_names.push(medname);
    });

    setMedicines(med_names);
  }

  async function getUpcomingAppts(patientId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const apptQuery = `http://hapi.fhir.org/baseR4/Encounter?subject=${patientId}&status=planned&_pretty=true`;

    let response = await fetch(apptQuery);
    let data = await response.json();

    if (typeof data.entry == "undefined") return;

    var appt_names = [];
    data.entry.forEach((appt) => {
      if (typeof appt.resource.statusHistory == "undefined") return;
      if (typeof appt.resource.statusHistory.length == 0) return;

      let medname =
        `Date: ${appt.resource.statusHistory[0]?.period?.start}\nPriority: ${appt.resource.priority?.coding[0]?.display}` +
        `\nReason: ${appt.resource.reasonCode[0]?.coding[0]?.display}`;
      appt_names.push(medname);
    });

    setAppts(appt_names);
  }

  async function getVitalStats(patientId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const vitalsQuery = `http://hapi.fhir.org/baseR4/Observation?patient=${patientId}&_pretty=true`;

    let response = await fetch(vitalsQuery);
    let data = await response.json();

    if (typeof data.entry == "undefined") return;

    var stats_names = [];
    data.entry.forEach((stat) => {
      let medname = `${stat.resource.code?.text}: ${stat.resource.valueQuantity?.value} ${stat.resource.valueQuantity?.unit}`;
      stats_names.push(medname);
    });

    setVitalStats(stats_names);
  }

  React.useEffect(() => {
    const patientId = localStorage.getItem("patientId");
    getMedicationName(patientId);
    getUpcomingAppts(patientId);
    getVitalStats(patientId);
  }, []);

  return (
    <div className={classes.root}>
      <LeftDrawer />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" key="title" noWrap>
            My Profile
          </Typography>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Grid container md spacing={2} key="maingrid">
          <Grid item md>
            <Paper className={classes.paper}>
              <Typography
                className={classes.title}
                color="textSecondary"
                gutterBottom key="mymedicines"
              >
                My Medicines
              </Typography>
              <ul>
                {medicines.map((m, i) => {
                  return <li key={i}>{m}</li>;
                })}
              </ul>
            </Paper>
          </Grid>
          <Grid item>
            <Paper className={classes.paper}>
              <Typography
                className={classes.title}
                color="textSecondary"
                gutterBottom
              >
                My Upcoming appointments
              </Typography>
              <ul>
                {appts.map((m) => {
                  return <li>{m}</li>;
                })}
              </ul>
            </Paper>
          </Grid>
          <Grid item md>
            <Paper className={classes.paper}>
              <Typography
                className={classes.title}
                color="textSecondary"
                gutterBottom
              >
                Vital Stats
              </Typography>
              <ul>
                {vitStats.map((m, i) => {
                  return <li key={i}>{m}</li>;
                })}
              </ul>
            </Paper>
          </Grid>
        </Grid>
      </main>
    </div>
  );
}
