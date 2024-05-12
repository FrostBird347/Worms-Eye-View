#version 120

uniform sampler2D colortex0;
uniform sampler2D depthtex0;

uniform float intensity;

varying vec2 texcoord;

void main() {
	vec3 colour = texture2D(colortex0, texcoord).rgb;
	float depth = texture2D(depthtex0, texcoord).r;

	if (depth < 0.9878) {
		depth = (pow(4, pow((depth - 0.03), 95)) - 0.95) - 0.0479;
	} else {
		depth = pow(depth + 0.025, pow(depth + 0.0148, pow(40, pow(depth + 0.02, pow(50, 0.75))))) - 0.99;
	}

	colour = mix(colour, vec3(depth), intensity);
	
	gl_FragColor = vec4(colour, 1.0);
}