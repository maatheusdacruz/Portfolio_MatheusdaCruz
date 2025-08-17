const express = require('express');
const { Home } = require('../controllers/ControllerHome');
const { About } = require('../controllers/ControllerAbout');
const { Projects } = require('../controllers/ControllerProjects');

const router = express.Router();

router.get('/', Home);
router.get('/about', About);
router.get('/projects', Projects);

module.exports = router;