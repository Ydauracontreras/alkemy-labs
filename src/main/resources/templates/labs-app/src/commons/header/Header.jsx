
import './Header.css';
import React from 'react';
import { fade, makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import MenuIcon from '@material-ui/icons/Menu';
import MailIcon from '@material-ui/icons/Mail';
import MoreIcon from '@material-ui/icons/MoreVert';
import LinkedInIcon from '@material-ui/icons/LinkedIn';
import GitHubIcon from '@material-ui/icons/GitHub';
const useStyles = makeStyles((theme) => ({
  grow: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
 
  sectionDesktop: {
    display: 'none',
    [theme.breakpoints.up('md')]: {
      display: 'flex',
    },
  },
  sectionMobile: {
    display: 'flex',
    [theme.breakpoints.up('md')]: {
      display: 'none',
    },
  },
}));

export default function PrimarySearchAppBar() {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const menuId = 'primary-search-account-menu';
 
  const mobileMenuId = 'primary-search-account-menu-mobile';
  const renderMobileMenu = (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{ vertical: 'top', horizontal: 'right' }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >

      <MenuItem>
          <IconButton aria-label="" color="inherit" href='/subjects'>
        </IconButton>
        <p>SUBJECTS</p>
      </MenuItem>
      <MenuItem>
        <IconButton aria-label="" color="inherit" href='/login'>
        </IconButton>
        <p>LOGIN</p>
      </MenuItem>
        <MenuItem>
        <IconButton aria-label="" color="inherit" href='/register'>
        </IconButton>
        <p>REGISTER</p>
      </MenuItem>
      <MenuItem onClick={handleProfileMenuOpen}>
            <IconButton aria-label="" color="inherit" href='/managers'
          aria-label="account of current user"
          aria-controls="primary-search-account-menu"
          aria-haspopup="true"
          color="inherit"
        >
        </IconButton>
        <p>MANAGERS</p>
      </MenuItem>
    </Menu>
  );

  return (
    <div className={classes.grow}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
           href='/'
            edge="start"
            className={classes.menuButton}
            color="inherit"
            aria-label="open drawer"
          >
             <img src="https://alkemy.org/assets/images/logo-header.png" alt="" />
          </IconButton>
             
 
          <div className={classes.grow} />
          <div className={classes.sectionDesktop}>
             <IconButton aria-label="" color="inherit" href='/subjects '>
               <label>SUBJECTS</label>
            </IconButton>
            <IconButton aria-label="" color="inherit" href='/login '>
             <label>LOGIN</label>
            </IconButton>
               <IconButton aria-label="" color="inherit" href='/register '>
             <label>REGISTER</label>
            </IconButton>
               <IconButton aria-label="" color="inherit" href='managers'>
             <label>ADMIN</label>
            </IconButton>
            </div>
          <div className={classes.sectionMobile}>
            <IconButton
              aria-label="show more"
              aria-controls={mobileMenuId}
              aria-haspopup="true"
              onClick={handleMobileMenuOpen}
              color="inherit"
            >
              <MoreIcon />
            </IconButton>
          </div>
        </Toolbar>
      </AppBar>
      {renderMobileMenu}
    </div>
  );
}

