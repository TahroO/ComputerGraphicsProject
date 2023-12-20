#version 330
out vec3 wert;

layout(location=0) in vec2 eckenAusJava;
layout(location=1) in vec3 farbenAusJava;

void main() {
    wert = farbenAusJava;

    float alpha = 0.1;

    mat2 m = mat2(cos(alpha), sin(alpha), -sin(alpha), cos(alpha));

    gl_Position = vec4(eckenAusJava*m, 0.0, 1.0);
}
