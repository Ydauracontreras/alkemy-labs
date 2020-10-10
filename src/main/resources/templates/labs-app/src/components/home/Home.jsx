import React from 'react';
import './Home.css'
import Register from '../register/Register';
import Button from '@material-ui/core/Button';
import Header from '../../commons/header/Header';
import Footer from '../../commons/footer/Footer';



const Home = () => {

return(
<div  className="p-col-12 p-md-6 p-lg-3">
    <Header/>
       <div className= "home-container">
  <img src="https://alkemy.org/assets/images/logo-header.png" alt=""/>
     <p>
         Desarrollado para Alkemy-Labs:
         <br/>
         <strong>Ydaura Contreras</strong>

     </p>
    
      
        </div>
              <Footer/>
          </div>

    )
}

export default Home;