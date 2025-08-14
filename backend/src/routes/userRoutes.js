import express from 'express';
import {home} from '../controllers/ControllerHome.js';
import {about} from '../controllers/ControllerAbout.js';

const router = express.Router();

router.get('/', home);
router.get('/about', about);

export default router;