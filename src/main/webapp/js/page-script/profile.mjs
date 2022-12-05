import {whenDomReady} from '../util.mjs';
import {handleAlerts} from '../shared/alert.mjs';

whenDomReady(() => {
    handleAlerts();
});
