import { toast } from 'react-toastify';

export const AlertService = {
    info: function (value) {

        toast.info(value);
    },

    success: function (value) {

        toast.success(value);
    },

    error: function (value) {

        toast.error(value);
    }
};

export default AlertService