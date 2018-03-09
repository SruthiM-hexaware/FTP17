var Point = (function () {
    function Point() {
    }
    Point.prototype.draw = function () {
        console.log('x' + this.x + 'y' + this.y);
    };
    return Point;
}());
var point = new Point();
point.x = 10;
point.y = 12;
point.draw();
