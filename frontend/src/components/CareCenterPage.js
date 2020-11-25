import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import LeftDrawer from './LeftDrawer';

const myStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(3),
  },
}));

export function CareCenterPage() {

  const classes = myStyles();

  return (
    <div className={classes.root}>
      <LeftDrawer />
      <h1>Care Center!</h1>
    </div>
  );
}
