#version 330
out vec3 theColor;
out vec3 rotatedPositions;
//out vec3 N;
//out mat4 transformationOut;
out vec3 normalOut;
out vec2 uvOut;

layout(location=0) in vec4 eckenAusJava;
layout(location=1) in vec3 farbenAusJava;
layout(location=2) in vec3 nVector;
layout(location=4) in vec2 uvCoords;

uniform mat4 transformation;
uniform mat4 projection;

void main() {

    vec4 rotated = transformation * eckenAusJava;
    //
    //    vec3 L = normalize(vec3(-5,0,0) - vec3(rotated));
    //    vec3 V = normalize(- rotated.xyz);
    normalOut = normalize(mat3(transformation) * nVector);
    //    vec3 R = reflect(-L,N);

    //    float I = max(dot(N,L) + pow(dot(R,V), 2), 0);

    gl_Position = projection * rotated;
    //    float ambientStrength = 0.5f;
    //    vec3 ambient = ambientStrength * farbenAusJava;
    theColor = farbenAusJava;
    rotatedPositions = rotated.xyz;
    uvOut = uvCoords;
    //    transformationOut = transformation;
    //    vec3 result = ambient * theColor;
    //    FragColor = vec4(result, 1.0);

}
