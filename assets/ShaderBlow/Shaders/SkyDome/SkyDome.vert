uniform mat4 g_WorldViewProjectionMatrix;
uniform float g_Time;
attribute vec3 inPosition;

attribute vec2 inTexCoord;
varying vec2 texCoord1;

varying float cycleTime;
uniform float m_Cycle;
uniform float m_CycleSpeed;

#ifdef SEPERATE_TEXCOORD
attribute vec2 inTexCoord2;
varying vec2 texCoord2;
#endif

#ifdef HAS_CLOUDS1
uniform vec2 m_CloudOffset1;
uniform float m_CloudDirection1;
uniform float m_CloudSpeed1;
varying vec2 cloudCoord1;
#endif

#ifdef HAS_CLOUDS2
uniform vec2 m_CloudOffset2;
uniform float m_CloudDirection2;
uniform float m_CloudSpeed2;
varying vec2 cloudCoord2;
#endif

#ifdef HAS_MOON
uniform float m_MoonSpeed;
uniform float m_MoonDirection;
varying vec2 moonCoord;
#endif

#ifdef HAS_VERTEXCOLOR
attribute vec4 inColor;
varying vec4 vertColor;
#endif

const float pi = 3.14159;

void main(){
texCoord1 = inTexCoord;

cycleTime = mod(g_Time,100.0);//* m_CycleSpeed;

#ifdef HAS_CLOUDS1
cloudCoord1 = inTexCoord;
float cloudTime1 = (g_Time*(m_CloudSpeed1*0.1));
cloudCoord1.x += (cloudTime1*sin((m_CloudDirection1*(pi/180.0))));
cloudCoord1.y += (cloudTime1*cos((m_CloudDirection1*(pi/180.0))));
#endif

#ifdef HAS_CLOUDS2
cloudCoord2 = inTexCoord + vec2(0.14);
float cloudTime2 = (g_Time*(m_CloudSpeed2*0.1));
cloudCoord2.x += (cloudTime2*sin((m_CloudDirection2*(pi/180.0))));
cloudCoord2.y += (cloudTime2*cos((m_CloudDirection2*(pi/180.0))));
#endif

#ifdef HAS_MOON
moonCoord = inTexCoord;
float time = (g_Time*(m_MoonSpeed*0.1));
moonCoord.x += (time*sin((m_MoonDirection*(pi/180.0))));
moonCoord.y += (time*cos((m_MoonDirection*(pi/180.0))));
#endif

#ifdef SEPERATE_TEXCOORD
texCoord2 = inTexCoord2;
#endif

#ifdef HAS_VERTEXCOLOR
vertColor = inColor;
#endif

gl_Position = g_WorldViewProjectionMatrix * vec4(inPosition, 1.0);
}