#version 120

uniform sampler2D colortex0;
uniform sampler2D depthtex0;

uniform float intensity;
uniform float width;
uniform float height;

varying vec2 texcoord;

vec3 getBlur(){
	vec3 blur = vec3(0.0);
	int q = 12;
	float qh = q / 2.0 - 0.5;
	float allStrengths = 0.0;
	
	vec2 pixel = vec2(1.0 / width, 1.0 / height);
	for(int i=0; i < q; i++){
		for(int j=0; j < q; j++){
			float strength = 1 - sin(length(vec2(i - qh, j - qh)) / qh);
			
			blur += texture2D(colortex0, texcoord + vec2(i - qh, j - qh) * pixel).rgb * strength;
			
			allStrengths += strength;
		}
	}
	blur /= allStrengths;
	return blur;
}

void main()
{
	vec3 color = texture2D(colortex0, texcoord).rgb;
	float depth = texture2D(depthtex0, texcoord).r;

	if (depth < 0.9878) {
		depth = (pow(4, pow((depth - 0.03), 95)) - 0.95) - 0.0479;
	} else {
		depth = pow(depth + 0.025, pow(depth + 0.0148, pow(40, pow(depth + 0.02, pow(50, 0.75))))) - 0.99;
	}

	depth = (depth * 25) - ((1 - intensity) * 25);

	vec3 blur = getBlur();
	color = mix(color, blur, clamp(depth, 0.0, 1.0));
	
	//color = vec3(depth);
	//color = blur;
	
	gl_FragColor = vec4(color, 1.0);
}