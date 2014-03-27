<!--
var r=new Array()
r[0]="<img src='../images/cc1.png' style='position:absolute;left:400px;top:400px;z-index:-1;'>";
r[1]="<img src='../images/cc2.png' style='position:absolute;left:400px;top:400px;z-index:-1;'>";
r[2]="";
var d=new Date();
if (d.getDay()==0) {
  k=2;}
else if (d.getDay()==1) {
  k=3;}
else if (d.getDay()==2) {
  k=3;}
else if (d.getDay()==3) {
  k=2;}
else if (d.getDay()==4) {
  k=2;}
else if (d.getDay()==5) {
  k=3;}
else if (d.getDay()==6) {
  k=2;}
var i=Math.floor(Math.random()*k);
document.write(r[i]);
//-->
