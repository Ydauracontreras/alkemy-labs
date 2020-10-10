import React, { Component } from "react";
import { AuthenticationService } from "../../service/AuthenticationService";
import { AlertService } from "../../service/AlertService";
import './Login.css';
import Header from '../../commons/header/Header';
import Button from '@material-ui/core/Button';


export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      user: {
        username: null,
        password: null,
      },
    };
    this.login = this.login.bind(this);
}
  login() {
    AuthenticationService.login(this.state.user).then((data) => {
        this.props.history.push("/");
      this.setState({
        visible: false,
        user: {
          username: null,
          password: null,
        },
      });
    });

    
  }

  render() {
    return (
   <div  className="p-col-12 p-md-6 p-lg-3">
        <Header/>

     <div className= "login-container">
               <img src="https://alkemy.org/assets/images/logo-header.png" alt=""/>
            <h2>LOGIN</h2>

            <form id="login">
              
            <label className="Registro-label">DNI</label>
              <div className="form-group">
                <input
                  className="col-12"
                  onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let  user = Object.assign({}, prevState.user);
                      user.username = val;
                      return { user };
                    });
                  }}
                ></input>
              </div>
               <label className="Registro-label">FILE</label>
              <div className="form-group">
                <input
                  className="col-12"
                  type="password"
                  onChange={(e) => {
                    let val = e.target.value;
                    this.setState((prevState) => {
                      let user = Object.assign({}, prevState.user);
                      user.password = val;
                      return { user };
                    });
                  }}
                ></input>
              </div>
            </form>
            <div className="form-group">
              <Button variant="outlined" color="primary" onClick={this.login}>
                LOGIN
              </Button>
                
            </div>
         
            </div>
          </div>
    );
  }

  showLoginDialog() {
    this.setState({
      visible: true,
    });
  }
}



