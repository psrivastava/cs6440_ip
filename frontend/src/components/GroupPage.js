import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import LeftDrawer from './LeftDrawer';
import Checkbox from '@material-ui/core/Checkbox';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import { Grid, Paper } from '@material-ui/core';
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

const { default: Axios } = require("axios");
const { REACT_APP_API_SERVER } = process.env;

const drawerWidth = 240;
const myStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  content: {
    flexGrow: 1,
    width: '1200px',
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(2),
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: `${drawerWidth}`,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
}));

const handleChange = () => {

}

const doSomething = async () => {
}

export function GroupPage() {

  const classes = myStyles();
  const [groupNames, setGroupNames] = React.useState([]);
  const [myGroups, setMyGroups] = React.useState([]);
  const [chat, setChat] = React.useState([]);

  async function getGroups(patientId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const qry = (typeof REACT_APP_API_SERVER == "undefined")?"http://localhost:8080/api/category":
          `${REACT_APP_API_SERVER}/api/category`

    let response = await fetch(qry);
    let data = await response.json();

    if (typeof data == "undefined") return;

    var data_names = [];
    data.forEach((stat) => {
      data_names.push(stat);
    });

    setGroupNames(data_names);
  }

  async function getMyGroups(userId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const qry = (typeof REACT_APP_API_SERVER == "undefined")?`http://localhost:8080/api/user-profile/${userId}`:
          `${REACT_APP_API_SERVER}/api/user-profile/${userId}`

    let response = await fetch(qry);
    let data = await response.json();

    if (typeof data == "undefined") return;

    var data_names = [];
    data.categories?.forEach((stat) => {
      data_names.push(stat);
    });

    setMyGroups(data_names);
  }

  React.useEffect(() => {
    const patientId = localStorage.getItem("patientId");
    const userId = localStorage.getItem("userId");
    getGroups();
    getMyGroups(userId);
  }, []);

  return (
    <div className={classes.root}>
      <LeftDrawer />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap>
            Groups explorer
          </Typography>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
      <Grid container spacing={2}>
        <Grid item md={4} sm={4}>
          <Paper>
            <FormGroup column>
              {
                groupNames.map((g) => {
                  return <FormControlLabel
                    control={
                      <Checkbox checked="false" onChange={handleChange} name={g.categoryId} />
                    }
                    label={g.categoryName}
                  />
                })
              }
            </FormGroup>
          </Paper>
        </Grid>
        <Grid item md={4} sm={4}>
          <Paper>
            <FormGroup column>
              {
                myGroups.map((g) => {
                  return <FormControlLabel
                    control={
                      <Checkbox checked="false" onChange={handleChange} name={g.categoryId} />
                    }
                    label={g.categoryName}
                  />
                })
              }
            </FormGroup>
          </Paper>
        </Grid>
        <Grid item md={4} sm={4}>
          <Paper>
            <ul>
              <li>bp</li>
              <li>weight</li>
            </ul>
          </Paper>
        </Grid>
      </Grid>
      </main>
    </div>
  );
}
