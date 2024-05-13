#version 120

uniform sampler2D colortex0;
uniform sampler2D depthtex0;

uniform float width;
uniform float height;
uniform float intensity;

varying vec2 texcoord;

float getDepth(vec2 coord){
	float depth = texture2D(depthtex0, coord).r;

	if (depth < 0.9878) {
		depth = (pow(4, pow((depth - 0.03), 95)) - 0.95) - 0.0479;
	} else {
		depth = pow(depth + 0.025, pow(depth + 0.0148, pow(40, pow(depth + 0.02, pow(50, 0.75))))) - 0.99;
	}

	return depth;
}

void main(){
	vec3 color = texture2D(colortex0, texcoord).rgb;
	
	float outline = 0.0;
	
	float size = 4.0 * intensity;
	
	float centreDepth = getDepth(texcoord);
	outline += clamp(centreDepth - getDepth(texcoord + vec2(size / width, 0)), 0.0, 1.0);
	outline += clamp(centreDepth - getDepth(texcoord - vec2(size / width, 0)), 0.0, 1.0);
	outline += clamp(centreDepth - getDepth(texcoord + vec2(0, size / height)), 0.0, 1.0);
	outline += clamp(centreDepth - getDepth(texcoord - vec2(0, size / height)), 0.0, 1.0);
	
	outline = 1.0 - clamp(outline * 51.2, 0.0, 1.0);
	
	color *= outline;
	
	gl_FragColor = vec4(color, 1.0);
}