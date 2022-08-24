import React, { Component } from "react";
import axios from "axios";

export default class AddCar extends Component {
  state = {
    carId: "",
    carNumber: "",
    ownerId: "",
    carSize: "Small",
    firstName: "",
    lastName: "",
    contactNumber: "",
    lotNumber: "",
    error: "",
    success: "",
  };

  onChange = this.onChange.bind(this);
  onSubmit = this.onSubmit.bind(this);

  componentDidMount() {
    let username = localStorage.username;
    if (!username) {
      this.props.history.push(`/`);
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    axios
      .get(`/api/lots/${this.state.carSize}`)
      .then((res) => {
        console.log(res);
        this.setState({
          lotNumber: res.data[0].lotNumber,
        });

        axios
          .post("/api/owners", {
            //ownerId: parseInt(this.state.ownerId),
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            contactNumber: this.state.contactNumber,
          })
          .then((res) => {
            axios
              .post("/api/cars", {
                //carId: parseInt(this.state.carId),
                carNumber: this.state.carNumber,
                ownerId: res.data.ownerId,
                lotNumber: this.state.lotNumber,
                carSize: this.state.carSize,
                entryDateAndTime: new Date().toLocaleString(),
                exitDateAndTime: null,
                amountPaid: null,
                totalTime: null,
              })
              .then((res) => {
                axios
                  .put("/api/lots", {
                    lotNumber: this.state.lotNumber,
                    size: this.state.carSize,
                    empty: false,
                  })
                  .then((res) => {
                    this.setState({
                      success: "Your car is parked successfully!",
                    });

                    window.location = "/cars";

                    console.log(res);
                  })
                  .catch((e) => console.log(e));
                console.log(res);
              })
              .catch((e) => console.log(e));

            console.log(res);
          })
          .catch((e) => {
            console.log(e);
          });

        console.log(res);
      })
      .catch((e) => {
        console.log("thelrlwjis a n  erroor");
        this.setState({
          error: "An error occured!",
        });
        console.log(e);
      });
  }

  render() {
    let message = "";
    if (this.state.success) {
      message = <p className="success-msg">{this.state.success}</p>;
    } else if (this.state.error) {
      message = <p className="error-msg">{this.state.error}</p>;
    }

    return (
      <>
        <div className="form-container">
          {message}

          <form onSubmit={this.onSubmit}>
            <fieldset className="form-field">
              <legend>Car Details</legend>

              <div className="form-row">
                <label className="form-label">Car Number</label>
                <input
                  required
                  className="form-input"
                  type="text"
                  name="carNumber"
                  value={this.state.carNumber}
                  onChange={this.onChange}
                />
              </div>

              {/* <div className="form-row">
                <label className="form-label">Car Size</label>
                <input
                  className="form-input"
                  type="text"
                  name="carSize"
                  value={this.state.carSize}
                  onChange={this.onChange}
                />
              </div> */}
              <div className="form-row">
                <label className="form-label">Car Size</label>
                <select
                  className="custom-select"
                  name="carSize"
                  onChange={this.onChange}
                >
                  <option value="Small">Small</option>
                  <option value="Medium">Medium</option>
                  <option value="Large">Large</option>
                </select>
              </div>

              <div className="form-row">
                <label className="form-label">First Name</label>
                <input
                  required
                  className="form-input"
                  type="text"
                  name="firstName"
                  value={this.state.firstName}
                  onChange={this.onChange}
                />
              </div>

              <div className="form-row">
                <label className="form-label">Last Name</label>
                <input
                  required
                  className="form-input"
                  type="text"
                  name="lastName"
                  value={this.state.lastName}
                  onChange={this.onChange}
                />
              </div>

              <div className="form-row">
                <label className="form-label">Contact Number</label>
                <input
                  required
                  className="form-input"
                  type="text"
                  name="contactNumber"
                  value={this.state.contactNumber}
                  onChange={this.onChange}
                />
              </div>
            </fieldset>
            <button type="submit" className="submit-btn">
              Park Car
            </button>
          </form>
          <hr />
        </div>

        {/* <form onSubmit={this.onSubmit}>
          <input
            type="text"
            placeholder="Car Id"
            name="carId"
            value={this.state.carId}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="Car Number"
            name="carNumber"
            value={this.state.carNumber}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="Car Size"
            name="carSize"
            value={this.state.carSize}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="ownerId"
            name="ownerId"
            value={this.state.ownerId}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="firstName"
            name="firstName"
            value={this.state.firstName}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="lastName"
            name="lastName"
            value={this.state.lastName}
            onChange={this.onChange}
          />

          <input
            type="text"
            placeholder="contactNumber"
            name="contactNumber"
            value={this.state.contactNumber}
            onChange={this.onChange}
          />

          <button type="submit">Add Car</button>
        </form> */}
      </>
    );
  }
}
