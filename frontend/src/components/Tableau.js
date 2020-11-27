import React, { Component } from "react";
import tableau from "tableau-api";

class Tableau extends Component {
  componentDidMount() {
    this.initViz();
  }

  initViz() {
    const vizUrl =
      "https://public.tableau.com/views/Drug-use-disorder-deaths/Dashboard1";
    const vizContainer = this.vizContainer;

    var options = {
      height: "2000px",
      width: "1200px",
      hideTabs: true,
      hideToolbar: true,
    };

    let viz = new window.tableau.Viz(vizContainer, vizUrl, options);
  }

  render() {
    return (
      <div
        ref={(div) => {
          this.vizContainer = div;
        }}
      ></div>
    );
  }
}

export default Tableau;
