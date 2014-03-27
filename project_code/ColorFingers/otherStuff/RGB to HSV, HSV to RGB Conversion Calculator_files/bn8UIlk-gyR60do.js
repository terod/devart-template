<!--

function rgbtgry(form) {
rr=eval(form.rr.value);gg=eval(form.gg.value);bb=eval(form.bb.value);
if (rr<0||rr>255||gg<0||gg>255||bb<0||bb>255||Math.ceil(rr)-Math.floor(rr)!=0||Math.ceil(gg)-Math.floor(gg)!=0||Math.ceil(bb)-Math.floor(bb)!=0) {document.getElementById('ertt').style.backgroundColor="#ffffff";
document.getElementById('benti').style.backgroundColor="#ffffff";
form.qwer.value=("RGB values must be integers between 0 and 255 inclusive.");}
else {
wav=Math.round(0.299*rr+0.114*bb+0.587*gg);
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
wavh=wav.toString(16);
  if (wavh.length==1) {wavh="0"+wavh;}
document.getElementById('benti').style.backgroundColor="#"+Roo+Gog+Boo;
document.getElementById('ertt').style.backgroundColor="#"+wavh+wavh+wavh;
form.qwer.value=("rgb(" + wav + ", " + wav + ", " + wav + ") \n" + "#" + wavh + wavh + wavh);}}
function resetrgbtgry(form) {
form.rr.value="red";
form.gg.value="green";
form.bb.value="blue";
form.qwer.value="";
document.getElementById('ertt').style.backgroundColor="#ffffff";
document.getElementById('benti').style.backgroundColor="#ffffff";}







function hsrgbv(form) {

if (form.wayy[0].checked) {
rr=eval(form.rr.value);gg=eval(form.gg.value);bb=eval(form.bb.value);
MM=Math.max(Math.max(rr,gg),bb)/255;
mm=Math.min(Math.min(rr,gg),bb)/255;
CC=MM-mm;
if (MM==0) {sat=0;}
else {sat=CC/MM;}
 if (rr-gg==0&&rr-bb==0) {
 huee=0;}
else {
 if (gg-bb>=0) {
 huee=Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}
 if (gg-bb<0) {
 huee=360-Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}}
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Hue: " + Math.round(huee) + "\u00B0 \n" + "Saturation: " + sat.toFixed(4) + "\n" + "Value: " + MM.toFixed(4));}

if (form.wayy[1].checked) {
hh=eval(form.hh.value);ss=eval(form.ss.value);vv=eval(form.vv.value);
cc=vv*ss;
xx=cc*(1-Math.abs((hh/60)%2 - 1));
if (0<=hh&&hh<60) {pr=cc; pg=xx; pb=0;}
if (60<=hh&&hh<120) {pr=xx; pg=cc; pb=0;}
if (120<=hh&&hh<180) {pr=0; pg=cc; pb=xx;}
if (180<=hh&&hh<240) {pr=0; pg=xx; pb=cc;}
if (240<=hh&&hh<300) {pr=xx; pg=0; pb=cc;}
if (300<=hh&&hh<=360) {pr=cc; pg=0; pb=xx;}
rr=Math.round((pr+vv-cc)*255);
gg=Math.round((pg+vv-cc)*255);
bb=Math.round((pb+vv-cc)*255);
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Red: " + rr + "\n" + "Green: " + gg + "\n" + "Blue: " + bb);}}
function resetv(form) {
form.rr.value="red";
form.gg.value="green";
form.bb.value="blue";
form.hh.value="hue";
form.ss.value="sat.";
form.vv.value="value";
form.qwer.value="";
document.getElementById('ertt').style.backgroundColor="#ffffff";}









function hsrgbL(form) {

if (form.wayy[0].checked) {
rr=eval(form.rr.value);gg=eval(form.gg.value);bb=eval(form.bb.value);
MM=Math.max(Math.max(rr,gg),bb)/255;
mm=Math.min(Math.min(rr,gg),bb)/255;
CC=MM-mm;
LL=0.5*(mm+MM)
if (LL==0) {sat=0;}
else {sat=CC/(1-Math.abs(2*LL-1));}
 if (rr-gg==0&&rr-bb==0) {
 huee=0;}
else {
 if (gg-bb>=0) {
 huee=Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}
 if (gg-bb<0) {
 huee=360-Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}}
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Hue: " + Math.round(huee) + "\u00B0 \n" + "Saturation: " + sat.toFixed(4) + "\n" + "Lightness: " + LL.toFixed(4));}

if (form.wayy[1].checked) {
hh=eval(form.hh.value);ss=eval(form.ss.value);ll=eval(form.ll.value);
cc=(1-Math.abs(2*ll-1))*ss;
xx=cc*(1-Math.abs((hh/60)%2 - 1));
if (0<=hh&&hh<60) {pr=cc; pg=xx; pb=0;}
if (60<=hh&&hh<120) {pr=xx; pg=cc; pb=0;}
if (120<=hh&&hh<180) {pr=0; pg=cc; pb=xx;}
if (180<=hh&&hh<240) {pr=0; pg=xx; pb=cc;}
if (240<=hh&&hh<300) {pr=xx; pg=0; pb=cc;}
if (300<=hh&&hh<=360) {pr=cc; pg=0; pb=xx;}
rr=Math.round((pr+ll-0.5*cc)*255);
gg=Math.round((pg+ll-0.5*cc)*255);
bb=Math.round((pb+ll-0.5*cc)*255);
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Red: " + rr + "\n" + "Green: " + gg + "\n" + "Blue: " + bb);}}
function resetl(form) {
form.rr.value="red";
form.gg.value="green";
form.bb.value="blue";
form.hh.value="hue";
form.ss.value="sat.";
form.ll.value="light";
form.qwer.value="";
document.getElementById('ertt').style.backgroundColor="#ffffff";}












function hsrgbI(form){


if (form.wayy[0].checked) {
rr=eval(form.rr.value);gg=eval(form.gg.value);bb=eval(form.bb.value);
ii=(rr+gg+bb)/3;
mm=Math.min(Math.min(rr,gg),bb)
if (ii==0) {sat=0;}
else{sat=1-mm/ii;}
if (rr-gg==0&&rr-bb==0) {
 huee=0;}
else {
 if (gg-bb>=0) {
 huee=Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}
 if (gg-bb<0) {
 huee=360-Math.acos((rr-0.5*gg-0.5*bb)/Math.sqrt(rr*rr+gg*gg+bb*bb-rr*gg-rr*bb-gg*bb))*180/Math.PI;}}
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Hue: " + Math.round(huee) + "\u00B0 \n" + "Saturation: " + sat.toFixed(4) + "\n" + "Intensity: " + Math.round(ii));}



if (form.wayy[1].checked) {
hh=eval(form.hh.value);ss=eval(form.ss.value);ii=eval(form.ii.value);
if (hh==0) {
  bb=Math.round(ii*(1-ss));
  gg=Math.round(ii*(1-ss));
  rr=Math.round(ii*(1+2*ss));}
if (hh>0&&hh<120) {
  bb=Math.round(ii*(1-ss));
  rr=Math.round(ii*(1+ss*Math.cos((hh/180)*Math.PI)/Math.cos(Math.PI*(60-hh)/180)));
  gg=Math.round(3*ii-rr-bb);}
if (hh==120) {
  bb=Math.round(ii*(1-ss));
  gg=Math.round(ii*(1+2*ss));
  rr=Math.round(ii*(1-ss));}
if (hh>120&&hh<240) { 
  rr=Math.round(ii*(1-ss));
  gg=Math.round(ii*(1+ss*Math.cos(Math.PI*(hh-120)/180)/Math.cos(Math.PI*(180-hh)/180)));
  bb=Math.round(3*ii-rr-gg);}
if (hh==240) {
  bb=Math.round(ii*(1+2*ss));
  gg=Math.round(ii*(1-ss));
  rr=Math.round(ii*(1-ss));}
if (hh>240&&hh<=360) {
  gg=Math.round(ii*(1-ss));
  bb=Math.round(ii*(1+ss*Math.cos(Math.PI*(hh-240)/180)/Math.cos(Math.PI*(300-hh)/180)));
  rr=Math.round(3*ii-gg-bb);}
if (rr>=0&&rr<=255&&gg>=0&&gg<=255&&bb>=0&&bb<=255) {
Roo=rr.toString(16);
  if (Roo.length==1) {Roo="0"+Roo;}
Gog=gg.toString(16);
  if (Gog.length==1) {Gog="0"+Gog;}
Boo=bb.toString(16);
  if (Boo.length==1) {Boo="0"+Boo;}
document.getElementById('ertt').style.backgroundColor="#"+Roo+Gog+Boo;
form.qwer.value=("Red: " + rr + "\n" + "Green: " + gg + "\n" + "Blue: " + bb);}
else {
document.getElementById('ertt').style.backgroundColor="#ffffff";
form.qwer.value=("These HSI inputs return RGB values out of the 0 to 255 range.");}
}}

function resetI(form) {
form.rr.value="red";
form.gg.value="green";
form.bb.value="blue";
form.hh.value="hue";
form.ss.value="sat.";
form.ii.value="int.";
form.qwer.value="";
document.getElementById('ertt').style.backgroundColor="#ffffff";}


//-->