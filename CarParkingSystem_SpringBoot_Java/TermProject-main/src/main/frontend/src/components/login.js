import React, { Component } from "react";
import axios from "axios";

export default class Login extends Component {
  state = {
    username: "",
    password: "",
    error: "",
  };

  onChange = this.onChange.bind(this);
  onSubmit = this.onSubmit.bind(this);

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    axios
      .get(`api/admins/${this.state.username}/${this.state.password}`)
      .then((res) => {
        if (res.data.length === 0) {
          this.setState({
            error: "Username or password is incorrect!",
          });
        } else {
          localStorage.setItem("username", res.data[0].username);
          window.location = "/addCar";
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    return (
      <>
        <div className="form-container">
          {this.state.error && <p className="error-msg">{this.state.error}</p>}
          <form onSubmit={this.onSubmit}>
            <fieldset className="form-field">
              <legend>Log In</legend>
              <div className="form-row">
                <label className="form-label">Username</label>
                <input
                  className="form-input"
                  type="text"
                  name="username"
                  value={this.state.username}
                  onChange={this.onChange}
                />
              </div>

              <div className="form-row">
                <label className="form-label">Password</label>
                <input
                  className="form-input"
                  type="password"
                  name="password"
                  value={this.state.password}
                  onChange={this.onChange}
                />
              </div>
            </fieldset>
            <button type="submit" className="submit-btn">
              Login
            </button>
          </form>
          <hr />
        </div>

        {/* {this.state.error && <p>{this.state.error}</p>}

        <form onSubmit={this.onSubmit}>
          <input
            type="text"
            placeholder="Username"
            name="username"
            value={this.state.carId}
            onChange={this.onChange}
          />

          <input
            type="password"
            placeholder="Password"
            name="password"
            value={this.state.carNumber}
            onChange={this.onChange}
          />

          <button type="submit">Login</button>
        </form> */}
      </>
    );
  }
}
