import React, { useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Link, useHistory } from "react-router-dom";
import { fakeAuth } from "../app/auth";

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: drawerWidth,
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0
  },
  drawerPaper: {
    width: drawerWidth,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(3),
  },
}));

export default function LeftDrawer() {
  const classes = useStyles();
  let isHealthcare = useRef(false);

  React.useEffect(() => {
    isHealthcare = (localStorage.getItem("isHealthcare") === "yes");
  });

  const logout = () => {
    fakeAuth.signout();
  };

  function HealthCareOptions(props) {
    return (
      <>
        <ListItem component={Link} to="/report" key="Report">
          <ListItemText primary="Report" />
        </ListItem>
        <ListItem component={Link} to="/dash" key="Dashboard">
          <ListItemText primary="Dashboard" />
        </ListItem>
        <ListItem component={Link} onClick={logout} key="Signout">
          <ListItemText primary="Sign Out" />
        </ListItem>
      </>
    );
  }

  function PatientOptions(props) {
    return (
      <>
        <ListItem component={Link} to="/home" key="Home">
          <ListItemText primary="Home" />
        </ListItem>
        <ListItem component={Link} to="/group" key="Groups">
          <ListItemText primary="Groups" />
        </ListItem>
        <ListItem component={Link} to="/dash" key="Dashboard">
          <ListItemText primary="Dashboard" />
        </ListItem>
        <ListItem component={Link} onClick={logout} key="Signout">
          <ListItemText primary="Sign Out" />
        </ListItem>
      </>
    );
  }

  function MenuItems(props) {
    if (props.isHealthcare == true) {
      return <HealthCareOptions />;
    }
    return <PatientOptions />;
  }

  return (
    <div>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        classes={{
          paper: classes.drawerPaper,
        }}
        anchor="left"
      >
        <div className={classes.toolbar} />
        <Divider />
        <List>
          <MenuItems isHealthcare={isHealthcare} />
        </List>
        <Divider />
      </Drawer>
    </div>
  );
}
