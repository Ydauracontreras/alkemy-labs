import axios from "axios";
import { restAPI } from "../components/_helpers/api.calls";
import { AuthenticationService } from "./AuthenticationService";


export default class SubjectService {
  getAll() {
    return restAPI.get("/subjects").then((res) => res.data);
  }

  getMySubjects() {
    let yo = AuthenticationService.currentUser().entityId;
    return restAPI.get(`/students/${yo}/subjects`).then((res) => res.data);
  } 


  postEnrollToSubjec(enrollInfo) {
    let yo = AuthenticationService.currentUser().entityId;
    return restAPI.post(`/students/${yo}/enrollements`, enrollInfo).then((res) => res.data);
  }
}


