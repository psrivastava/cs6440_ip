import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import LeftDrawer from './LeftDrawer';
import Iframe from "react-iframe";
import TableauReport from 'tableau-react-embed';
import Tableau from './Tableau';
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";

const drawerWidth = 240;
const myStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(6),
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: `${drawerWidth}`,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
}));

export function DashboardPage() {
  const classes = myStyles();

  return (
    <div className={classes.root}>
      <LeftDrawer />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap>
            Dashboard - Impact of drugabuse
          </Typography>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
      <Tableau/>
      </main>
    </div>
  );
}
