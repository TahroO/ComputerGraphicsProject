#version 330

in vec3 theColor;
//in vec3 N;
in vec3 rotatedPositions;
//in mat4 transformationOut;
in vec3 normalOut;
in vec2 uvOut;

out vec3 pixelFarbe;

uniform sampler2D smplr;

void main() {

    vec3 L = normalize(vec3(-10,0,0) - vec3(rotatedPositions));
    vec3 V = normalize(- rotatedPositions.xyz);
    vec3 N = normalize(normalOut);
    vec3 R = reflect(-L,N);

    vec3 textureColor = texture(smplr, uvOut).rgb;

    // ambient light 0.3 / intensität 0.6
    float I = 0.0f + 1.0f * (max(0, dot(N,L)) + pow(max(dot(R,V), 0), 100));

    pixelFarbe = I * textureColor;
}