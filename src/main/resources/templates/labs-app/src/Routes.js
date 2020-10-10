import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useHistory,
} from "react-router-dom";
import Register from './components/register/Register';
import Home from './components/home/Home';
import Login from './components/login/Login'
import Subject from './components/subjects/Subject'


const Routes = () => {
  const history = useHistory();

  return (
    <Router>
      <Switch history={history}>
        <Route exact path="/" component={Home} />
         <Route exact path="/register" component={Register} />
             <Route exact path="/login" component={Login} />
                    <Route exact path="/subjects" component={Subject} />

      </Switch>
    </Router>
  );
};
export default Routes;
