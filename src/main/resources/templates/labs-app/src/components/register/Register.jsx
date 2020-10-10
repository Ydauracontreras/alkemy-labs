import React, { Component, useState } from "react";

import { AuthenticationService } from "../../service/AuthenticationService";
import { AlertService } from "../../service/AlertService";
import Header from '../../commons/header/Header';
import Button from '@material-ui/core/Button';
import './Register.css'


export default class Register extends Component {
  constructor() {
    super();
    this.state = {
      visible: false,
      user: {
        userType: null,
        name: null,
        lastName: null,
        dni: null,
      },

    };

    this.registrar = this.registrar.bind(this);

  }

  setUserType(event) {
    console.log(event.target.value);
  }
  
 

  registrar() {
 
    AuthenticationService.register(this.state.user).then((data) => {
      AlertService.success("Registrade con exito!");
      this.setState({
        visible: false,
        usuario: {
       userType: null,
        name: null,
        lastName: null,
        dni: null,
        },
      });
    });
  }

  render() {
    return (
         
               <div  className="p-col-12 p-md-6 p-lg-3">
        <Header/>

     <div className= "register-container">          
      <img src="https://alkemy.org/assets/images/logo-header.png" alt=""/>
            <h2>REGISTER</h2>
            <form id="" className="registro-dialog">
               <label htmlFor="userType">USERTYPE</label>
            <div className ="radio-button" onChange={this.setUserType.bind(this)}>
      <label htmlFor="name">manager</label>
      < input
          type="radio" 
           name="gender"
            value="MANAGER"
             onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.userType = val;
                      return { user };
                    });
                  }}
                  />
                      <label htmlFor="name">student</label>
        <input
          type="radio" 
           name="gender"
            value="STUDENT"
             onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.userType = val;
                      return { user };
                    });
                  }}
                  />
      </div>
      <label htmlFor="name">NAME</label>
              <div className="form-group">
          
                <input
                  value={this.state.user.name}
                  id="name"
                  onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.name = val;
                      return { user };
                    });
                  }}
                />
              </div>
                  <label htmlFor="lastName">LASTNAME</label>
              <div className="form-group">
                <input
                  value={this.state.user.lastName}
                  id="lastName"
                  onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.lastName = val;
                      return { user };
                    });
                  }}
                />
              </div>
           <div>
                <label htmlFor="dni">DNI</label>
                  <div className="form-group">
                <input
                  value={this.state.user.dni}
                  id="dni"
                  onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.dni = val;
                      return { user };
                    });
                  }}
                />
              </div>
             </div>
            </form>

            <div className="form-group">

                  <Button variant="outlined" color="primary" onClick={this.registrar}>
                    REGISTER
                </Button>

            </div>
          </div>
      </div>
    );
  }

  showRegistrationDialog() {
    this.setState({
      visible: true,
    });
  }
}