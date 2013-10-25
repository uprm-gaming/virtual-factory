#define ATTENUATION
//#define HQ_ATTENUATION

uniform mat4 g_WorldViewProjectionMatrix;
uniform mat4 g_WorldViewMatrix;
uniform mat3 g_NormalMatrix;
uniform mat4 g_ViewMatrix;

varying vec3 mat;

uniform vec4 m_Ambient;
uniform vec4 m_Diffuse;

#if defined(SPECULAR_LIGHTING)
varying vec3 SpecularSum;
uniform float m_Shininess;

#if defined(MATERIAL_COLORS)
uniform vec4 m_Specular;
#endif

#endif

uniform vec4 g_LightColor;
uniform vec4 g_LightPosition;
uniform vec4 g_AmbientLightColor;

varying vec2 texCoord;

#ifdef SEPERATE_TEXCOORD
    attribute vec2 inTexCoord2;
    varying vec2 texCoord2;
#endif

#ifdef SEPERATE_TEXCOORD2
    attribute vec2 inTexCoord3;
    varying vec2 texCoord3;
#endif

varying vec3 AmbientSum;
varying vec4 DiffuseSum;

attribute vec3 inPosition;
attribute vec2 inTexCoord;
attribute vec3 inNormal;

varying vec3 lightVec;
//varying vec4 spotVec;

#ifdef VERTEX_COLOR
  attribute vec4 inColor;
  varying vec4 vColor;
#endif

#ifndef VERTEX_LIGHTING
  attribute vec4 inTangent;


  #ifndef NORMALMAP
    varying vec3 vNormal;
  #endif
//  varying vec3 vPosition;
  varying vec3 vViewDir;
  varying vec4 vLightDir;
#else
  varying vec2 vertexLightValues;
  uniform vec4 g_LightDirection;
#endif

#if defined(REFLECTION) || defined(IBL) || defined(IBL_SIMPLE) || defined(FOG_SKY)
    varying vec3 I;
    uniform vec3 g_CameraPosition;
    uniform mat4 g_WorldMatrix;
#endif 

#if defined(REFLECTION) || defined(IBL) || defined(IBL_SIMPLE)
    varying vec3 refVec;
#endif 


#ifdef FOG
    varying float fog_z;
#endif







// JME3 lights in world space
void lightComputeDir(in vec3 worldPos, in vec4 color, in vec4 position, out vec4 lightDir){
    float posLight = step(0.5, color.w);
    vec3 tempVec = position.xyz * sign(posLight - 0.5) - (worldPos * posLight);
    lightVec = tempVec;

    #ifdef ATTENUATION
     float dist = length(tempVec);
     lightDir.w = clamp(1.0 - position.w * dist * posLight, 0.0, 1.0);
     lightDir.xyz = tempVec / vec3(dist);
    #else
     lightDir = vec4(normalize(tempVec), 1.0);
    #endif
}

#ifdef VERTEX_LIGHTING
  float lightComputeDiffuse(in vec3 norm, in vec3 lightdir){
      return max(0.0, dot(norm, lightdir));
  }

  #if defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
  float lightComputeSpecular(in vec3 norm, in vec3 viewdir, in vec3 lightdir, in float shiny){
    if (shiny <= 1.0){
          return 0.0;
      }
      #ifndef LOW_QUALITY
        vec3 H = (viewdir + lightdir) * vec3(0.5);
        return pow(max(dot(H, norm), 0.0), shiny);
      #else
        return 0.0;
        #endif
  }
  #endif

vec2 computeLighting(in vec3 wvPos, in vec3 wvNorm, in vec3 wvViewDir, in vec4 wvLightPos){

   vec4 lightDir;
     lightComputeDir(wvPos, g_LightColor, wvLightPos, lightDir);
     float spotFallOff = 1.0;
     if(g_LightDirection.w != 0.0){
          vec3 L=normalize(lightVec.xyz);
          vec3 spotdir = normalize(g_LightDirection.xyz);
          float curAngleCos = dot(-L, spotdir);    
          float innerAngleCos = floor(g_LightDirection.w) * 0.001;
          float outerAngleCos = fract(g_LightDirection.w);
          float innerMinusOuter = innerAngleCos - outerAngleCos;
          spotFallOff = clamp((curAngleCos - outerAngleCos) / innerMinusOuter, 0.0, 1.0);
     }
     float diffuseFactor = lightComputeDiffuse(wvNorm, lightDir.xyz);
   #if defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
float specularFactor = lightComputeSpecular(wvNorm, wvViewDir, lightDir.xyz, m_Shininess);
    #endif 
 #if !defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
     float specularFactor = 0.0;
   #endif  

     return vec2(diffuseFactor, specularFactor) * vec2(lightDir.w)*spotFallOff;

  //   float diffuseFactor = lightComputeDiffuse(wvNorm, lightDir.xyz);

}
#endif


 



void main(){


   vec4 pos = vec4(inPosition, 1.0);
   gl_Position = g_WorldViewProjectionMatrix * pos;
   texCoord = inTexCoord;

#ifdef SEPERATE_TEXCOORD
        texCoord2 = inTexCoord2;
    #endif

#ifdef SEPERATE_TEXCOORD2
        texCoord3 = inTexCoord3;
    #endif


   vec3 wvPosition = (g_WorldViewMatrix * pos).xyz;
   vec3 wvNormal  = normalize(g_NormalMatrix * inNormal);
   vec3 viewDir = normalize(-wvPosition);

       //vec4 lightColor = g_LightColor[gl_InstanceID];
       //vec4 lightPos   = g_LightPosition[gl_InstanceID];
       //vec4 wvLightPos = (g_ViewMatrix * vec4(lightPos.xyz, lightColor.w));
       //wvLightPos.w = lightPos.w;

   vec4 wvLightPos = (g_ViewMatrix * vec4(g_LightPosition.xyz,clamp(g_LightColor.w,0.0,1.0)));
   wvLightPos.w = g_LightPosition.w;
   vec4 lightColor = g_LightColor;

   #if defined(NORMALMAP) || defined(NORMALMAP_1) || defined(NORMALMAP_2) || defined(NORMALMAP_3) && !defined(VERTEX_LIGHTING) 
     vec3 wvTangent = normalize(g_NormalMatrix * inTangent.xyz);
     vec3 wvBinormal = cross(wvNormal, wvTangent);


     mat3 tbnMat = mat3(wvTangent, wvBinormal * -inTangent.w,wvNormal);

     
     mat = vec3(1.0) * tbnMat;
     mat = normalize(mat);
//     vPosition = wvPosition * tbnMat;
  //   vViewDir  = viewDir * tbnMat;
     vViewDir  = -wvPosition * tbnMat;

     lightComputeDir(wvPosition, lightColor, wvLightPos, vLightDir);
     vLightDir.xyz = (vLightDir.xyz * tbnMat).xyz;

   #elif !defined(VERTEX_LIGHTING)
     vNormal = wvNormal;

//     vPosition = wvPosition;
     vViewDir = viewDir;


     lightComputeDir(wvPosition, lightColor, wvLightPos, vLightDir);
     
     #ifdef V_TANGENT
        vNormal = normalize(g_NormalMatrix * inTangent.xyz);
        vNormal = -cross(cross(vLightDir.xyz, vNormal), vNormal);
     #endif
   #endif

   //computing spot direction in view space and unpacking spotlight cos
//   spotVec = (g_ViewMatrix * vec4(g_LightDirection.xyz, 0.0) );
//   spotVec.w  = floor(g_LightDirection.w) * 0.001;
//   lightVec.w = fract(g_LightDirection.w);


   lightColor.w = 1.0;

   #ifdef MATERIAL_COLORS
      AmbientSum  = (m_Ambient  * g_AmbientLightColor).rgb;
      DiffuseSum  = m_Diffuse  * lightColor;
        #if defined(SPECULAR_LIGHTING)
      SpecularSum = (m_Specular * lightColor).rgb;
        #endif

    #else
      AmbientSum  = vec3(0.2, 0.2, 0.2) * g_AmbientLightColor.rgb; // Default: ambient color is dark gray
      DiffuseSum  = lightColor;
        #if defined(SPECULAR_LIGHTING)
      SpecularSum = (lightColor).rgb;
  //    SpecularSum = vec3(0.0);
        #endif
    #endif


    #ifdef VERTEX_COLOR
  //    AmbientSum *= inColor.rgb;
  //    DiffuseSum *= inColor;
      vColor = inColor;
    #endif

 
    #ifdef VERTEX_LIGHTING
      vertexLightValues = computeLighting(wvPosition, wvNormal, viewDir, wvLightPos);
    #endif


#if defined (REFLECTION) || defined (IBL) || defined(FOG_SKY) || defined(IBL_SIMPLE)
       vec3 worldPos = (g_WorldMatrix * pos).xyz;
       I = normalize( g_CameraPosition -  worldPos  ).xyz;
#endif

#if defined (REFLECTION) || defined (IBL) || defined(IBL_SIMPLE)
//Reflection vectors calculation

       vec3 N = normalize( (g_WorldMatrix * vec4(inNormal, 0.0)).xyz );      
       refVec = reflect(I, N);

    #endif


#ifdef FOG
    fog_z = gl_Position.z;
#endif

}