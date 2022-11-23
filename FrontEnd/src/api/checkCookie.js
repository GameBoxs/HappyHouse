let result = null;
result = function get_cookie() {
    var value = document.cookie.match('(^|;) ?' + 'JWT-Token' + '=([^;]*)(;|$)');
    return value? value[2] : null;
}
export default result;