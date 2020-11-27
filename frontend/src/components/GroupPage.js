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
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";

const { default: Axios } = require("axios");
const { REACT_APP_API_SERVER } = process.env;

const drawerWidth = 240;
const myStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
  },
  content: {
    flexGrow: 1,
    width: "1200px",
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

const doSomething = async () => {};

export function GroupPage() {
  const classes = myStyles();
  const [groupNames, setGroupNames] = React.useState([]);
  const [myGroups, setMyGroups] = React.useState([]);
  const [messages, setMessages] = React.useState([
    ["Me", "Hey man how are you doing"],
    ["Brandon", "I am giving it back"]
  ]);
  const [currentGroup, setCurrentGroup] = React.useState("1");

  async function getGroups(patientId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const qry =
      typeof REACT_APP_API_SERVER == "undefined"
        ? "http://localhost:8080/api/category"
        : `${REACT_APP_API_SERVER}/api/category`;

    let response = await fetch(qry);
    let data = await response.json();

    if (typeof data == "undefined") return;

    let data_names = [];

    data.forEach((stat) => {
      stat.checked = false;
      stat.name = `all-${stat.categoryId}`;
      data_names.push(stat);
    });

    setGroupNames(data_names);
  }

  async function getMyGroups(userId) {
    //console.log("--------------- patient:", patientId, " ----------------------");
    const qry =
      typeof REACT_APP_API_SERVER == "undefined"
        ? `http://localhost:8080/api/user-profile/${userId}`
        : `${REACT_APP_API_SERVER}/api/user-profile/${userId}`;

    let response = await fetch(qry);
    let data = await response.json();

    if (typeof data == "undefined") return;

    var data_names = [];

    data.categories?.forEach((stat) => {
      stat.checked = true;
      stat.name = `my-${stat.categoryId}`;
      data_names.push(stat);
    });

    setMyGroups(data_names);
  }

  const handleChangeAll = (idx, e) => {
    //e.preventDefault();
    groupNames[idx].checked = !groupNames[idx].checked;
    setGroupNames(groupNames);
  };

  const handleChangeMy = (event) => {
    setCurrentGroup(event.target.value.toString());
  };

  const handleSend = (event) => {

  };

  const Messages = props => props.data.map(m => m[0] !== '' ?
(<li key={m[0]}><strong>{m[0]}</strong> : <div className="innermsg">{m[1]}</div></li>)
: (<li key={m[1]} className="update">{m[1]}</li>) );

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
              <Typography
                className={classes.title}
                color="textSecondary"
                gutterBottom
              >
                All groups
              </Typography>
              <FormGroup column="true">
                {groupNames.map((g, idx) => {
                  return (
                    <FormControlLabel
                      control={
                        <Checkbox
                          key={g.name}
                          checked={g.checked}
                          onChange={handleChangeAll.bind(this, idx)}
                          name={g.name}
                          color="primary"
                        />
                      }
                      label={g.categoryName}
                    />
                  );
                })}
              </FormGroup>
            </Paper>
          </Grid>
          <Grid item md={4} sm={4}>
            <Paper>
              <FormControl component="fieldset">
                <FormLabel component="legend">My Groups</FormLabel>
                <RadioGroup
                  name="mygroups"
                  value={currentGroup}
                  onChange={handleChangeMy}
                >
                  {myGroups.map((g, idx) => {
                    return (
                      <FormControlLabel
                        key={"my-" + g.categoryId}
                        value={g.categoryId}
                        control={<Radio />}
                        label={g.categoryName}
                        name={"my-" + g.categoryId}
                      />
                    );
                  })}
                </RadioGroup>
              </FormControl>
            </Paper>
          </Grid>
          <Grid item md={4} sm={4}>
            <Paper>
              <Typography
                className={classes.title}
                color="textSecondary"
                gutterBottom
              >
                Chat
              </Typography>
              <Messages data={messages} />
              <div id="sendform">
                <form
                  onSubmit={(e) => handleSend(e)}
                  style={{ display: "flex" }}
                >
                  <input
                    id="m"
                    //onChange={(e) => setInput(e.target.value.trim())}
                  />
                  <button style={{ width: "75px" }} type="submit">
                    Send
                  </button>
                </form>
              </div>
            </Paper>
          </Grid>
        </Grid>
      </main>
    </div>
  );

  /*
  const [value, setValue] = React.useState("female");

  const handleChange = (event) => {
    setValue(event.target.value);
  };

  return (
    <>
    <Grid container spacing={2}>
          <Grid item md={4} sm={4}>
      <Paper>
        <FormControl component="fieldset">
          <FormLabel component="legend">Gender</FormLabel>
          <RadioGroup
            aria-label="gender"
            name="gender1"
            value={value}
            onChange={handleChange}
          >
            <FormControlLabel
              value="female"
              control={<Radio />}
              label="Female"
            />
            {myGroups.map((g, idx) => {
                    return (
                      <FormControlLabel
                        value={g.categoryId}
                        control={<Radio />}
                        label={g.categoryName}
                        name={"my-" + g.categoryId}
                      />
                    );
                  })}
          </RadioGroup>
        </FormControl>
      </Paper>
      </Grid>
      </Grid>
    </>
  );
  */
}
