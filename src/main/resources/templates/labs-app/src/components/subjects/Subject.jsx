import React, {Component}  from "react";
import "./Subject.css";
import Header from "../../commons/header/Header";
import SubjectService from '../../service/SubjectService';

export default class Categoria extends Component {
  constructor() {
    super();
    this.state = {
      visible: false,
      subjects: [],
    };
    this.subjectService = new SubjectService();
  }

  componentDidMount() {
    this.subjectService
      .getAll()
      .then((data) => this.setState({ subjects: data }));
  }

  render() {
    return (
      <div className="container-categoria">
        <Header />
        <div className="p-grid">
          {this.state.subjects.map((subject) => {
            return (
              <div className="p-col-12 p-md-6 p-lg-3">
               
                  <h1>{subject.name}</h1>
                
           
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}