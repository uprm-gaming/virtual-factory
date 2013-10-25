uniform vec4 m_ColorNight;
uniform vec4 m_ColorDay;
uniform sampler2D m_SkyNightMap;
uniform sampler2D m_FogAlphaMap;
uniform vec4 m_FogColor;
uniform vec4 m_FogNightColor;

varying float cycleTime;
uniform bool m_CycleDayToNight;
uniform bool m_CycleNightToDay;

varying vec2 texCoord1;
uniform float m_Alpha;

uniform sampler2D m_CloudMap1;
uniform float m_CloudsAlpha;

#ifdef HAS_CLOUDS1
varying vec2 cloudCoord1;
#endif

#ifdef HAS_CLOUDS2
varying vec2 cloudCoord2;
#endif

#ifdef HAS_MOON
uniform sampler2D m_MoonMap;
varying vec2 moonCoord;
#endif

#ifdef HAS_COLORRAMP1
uniform vec4 m_CloudColorRamp1;
#endif

#ifdef HAS_COLORRAMP2
uniform vec4 m_CloudColorRamp2;
#endif

#ifdef HAS_LIGHTMAP
uniform sampler2D m_LightMap;
#ifdef SEPERATE_TEXCOORD
varying vec2 texCoord2;
#endif
#endif

#ifdef HAS_VERTEXCOLOR
varying vec4 vertColor;
#endif

void main(){
vec4 color = vec4(1.0);
color *= texture2D(m_SkyNightMap, texCoord1);

#ifdef HAS_VERTEXCOLOR
color *= vertColor;
#endif

#ifdef HAS_MOON
vec4 moon = texture2D(m_MoonMap, moonCoord);
color = mix(color, moon, moon.a);
#endif

vec4 fColor = mix(m_ColorNight, m_ColorDay, m_Alpha);
color = mix(color, fColor, m_Alpha);
vec4 fogColor = mix(m_FogNightColor, m_FogColor, m_Alpha);

vec4 c_Color;
#if defined(HAS_COLORRAMP1) || defined(HAS_COLORRAMP2)
vec4 n_Color;
#endif
#if defined(HAS_CLOUDS1) || defined(HAS_CLOUDS2)
vec4 cloud;
#endif

#ifdef HAS_CLOUDS1
cloud = texture2D(m_CloudMap1, cloudCoord1);
c_Color = vec4(1.0,1.0,1.0,cloud.r*m_CloudsAlpha);
#ifdef HAS_COLORRAMP1
n_Color = vec4(vec3(m_CloudColorRamp1.rgb*cloud.r),cloud.r);
c_Color = mix(c_Color, n_Color, 0.25f);
#endif
color = mix(color,c_Color,cloud.r*m_CloudsAlpha);
#endif
#ifdef HAS_CLOUDS2
cloud = texture2D(m_CloudMap1, cloudCoord2);
c_Color = vec4(1.0,1.0,1.0,cloud.r*m_CloudsAlpha);
#ifdef HAS_COLORRAMP2
n_Color = vec4(vec3(m_CloudColorRamp2.rgb*cloud.r),cloud.r);
c_Color = mix(c_Color, n_Color, 0.25f);
#endif
color = mix(color,c_Color,cloud.r*m_CloudsAlpha);
#endif

float fogAlpha = 1.0-texture2D(m_FogAlphaMap, texCoord1).r;
color = mix(color,fogColor,fogAlpha);

 if(color.a < 0.01){
     discard;
 }

gl_FragColor = color;
}