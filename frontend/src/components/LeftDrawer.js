import React from 'react';
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
import { Link } from "react-router-dom";

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
          <ListItem component={Link} to="/home" key="Home">
            <ListItemText primary="Home" />
          </ListItem>
          <ListItem component={Link} to="/group" key="Groups">
            <ListItemText primary="Groups" />
          </ListItem>
          <ListItem component={Link} to="/report" key="Report">
            <ListItemText primary="Report" />
          </ListItem>
          <ListItem component={Link} to="/dash" key="Dashboard">
            <ListItemText primary="Dashboard" />
          </ListItem>
          <ListItem component={Link} to="/signout" key="Signout">
            <ListItemText primary="Sign Out" />
          </ListItem>
        </List>
        <Divider />
      </Drawer>
    </div>
  );
}
