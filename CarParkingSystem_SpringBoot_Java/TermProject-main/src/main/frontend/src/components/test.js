import React, { Component } from "react";
import axios from "axios";

export default class Test extends Component {
  state = {
    username: "",
  };

  componentDidMount() {
    axios
      .get("/api/lots")
      .then((res) => {
        console.log(res);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    return (
      <>
        <h1>Hello world</h1>
        <h3>Smile :) {this.state.username}</h3>
      </>
    );
  }
}
