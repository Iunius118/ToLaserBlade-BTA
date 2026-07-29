#version 410 core

out vec4 FragColor;

in vec4 Color;
in vec2 LightMapCoord;
in vec3 Light;

uniform float uLightMapStrength;
uniform float uAlphaTest;
uniform vec4 uColor;
uniform vec2 uViewPortSize;

layout (std140) uniform FogState {
    vec4 color;
    int mode;
    float start;
    float end;
    float density;
} fogState;

layout (std140) uniform Matrices {
    mat4 projection;
    mat4 projectionInv;
    mat4 view;
    mat4 viewInv;
} matrices;

uniform sampler2D lighttex;

float distance() {
    vec4 fragPos = matrices.projectionInv * vec4((gl_FragCoord.xy / uViewPortSize) * 2.0 - 1.0, gl_FragCoord.z * 2.0 - 1.0, 1.0);
    fragPos /= fragPos.w;

    return length(fragPos.xyz);
}

// Taken from https://github.com/hughsk/glsl-fog
float fogFactorLinear() {
    return 1.0 - clamp(((fogState.end - (distance())) / (fogState.end - fogState.start)), 0.0, 1.0);
}

float fogFactorExp() {
    return 1.0 - clamp(exp(-fogState.density * (distance())), 0.0, 1.0);
}

float fogFactorExp2() {
    float LOG2 = -1.442695;
    float d = fogState.density * (distance());
    return 1.0 - clamp(exp2(d * d * LOG2), 0.0, 1.0);
}


vec4 computeFog(vec4 color) {
    float f = 0;
    if (fogState.mode == 0) { // Linear
        f = fogFactorLinear();
    }
    else if (fogState.mode == 1) { // EXP
        f = fogFactorExp();
    }
    else if (fogState.mode == 2) { // EXP2
        f = fogFactorExp2();
    }
    return vec4(mix(color.rgb, fogState.color.rgb, f), color.a);
}

void main() {
    vec4 lightColor = mix(vec4(1, 1, 1, 1), texture(lighttex, LightMapCoord), uLightMapStrength);

    vec4 oColor = lightColor * Color * uColor;
    vec4 litColor = vec4(oColor.rgb * clamp(Light, 0, 1), oColor.a);

    FragColor = computeFog(litColor);
    if (FragColor.a < uAlphaTest) {
        discard;
    }
}
