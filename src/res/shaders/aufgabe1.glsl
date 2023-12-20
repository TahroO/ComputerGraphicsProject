#version 330
out vec3 pixelFarbe;
//uniform vec2 u_resolution;

// refactor drawrect to be used inside rotated rect

void drawRect(vec2 start, vec2 dimension, vec3 color) {

    vec2 coordinatesPixel = gl_FragCoord.xy;

    if(coordinatesPixel.x >= start.x && coordinatesPixel.x <= start.x + dimension.x
    && coordinatesPixel.y >= start.y && coordinatesPixel.y <= start.y + dimension.y) {
        pixelFarbe = color;
    }
}

void drawCircle(vec2 midpoint, float radius, vec3 color) {
    vec2 midPoint = midpoint;
    vec2 coordinatePixel = gl_FragCoord.xy;
    float distance = distance(midPoint, coordinatePixel);
    if(distance <= radius) {
        pixelFarbe = color;
    }
}

void drawRotatedRect(float alpha) {
    vec2 coordinatePixel = gl_FragCoord.xy;
    mat2 m = mat2(cos(alpha), sin(alpha), -sin(alpha), cos(alpha));

    vec2 newCoord = coordinatePixel * m;

//    drawRect(vec2(newCoord.x, newCoord.y), vec2(300,100), vec3(0.2,0.5,1.0));

    if(newCoord.x >= 300 && newCoord.x <= 600
    && newCoord.y >= 400 && newCoord.y <= 500) {
        pixelFarbe = vec3(0.0, 1.0, 0.0);
    }
}


void main() {
    pixelFarbe = vec3(1.0,0.0,1.0);
    drawCircle(vec2(400.00, 400.00), 50.0f, vec3(0.0, 1.0, 0.0));
    drawCircle(vec2(500.00, 500.00), 100.0f, vec3(0.5, 0.8, 0.2));
    drawRect(vec2(300, 400), vec2(300, 100), vec3(0.2, 0.5, 0.7));
    drawRect(vec2(125, 125), vec2(125, 125), vec3(0.2, 0.5, 0.7));
    drawRotatedRect(0.2);

}
