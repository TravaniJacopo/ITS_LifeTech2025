const container = document.createElement("div");
container.className = "container";
document.body.appendChild(container);


const div1 = document.createElement("div");
const div2 = document.createElement("div");
const h1 = document.createElement("h1");
const p = document.createElement("p");

div1.className = "row";
div1.style.backgroundColor = "rgb(14, 14, 48)";
div1.style.color = "white";
div2.className = "col";
h1.textContent = "Trieste";
p.textContent = "Una città di confine tra mare e cultura mitteleuropea";
div2.appendChild(h1);
div2.appendChild(p);
div1.appendChild(div2);
container.appendChild(div1);

const div3 = document.createElement("div");
const div4 = document.createElement("div");
const a1 = document.createElement("a");
const a2 = document.createElement("a");
const a3 = document.createElement("a");
const a4 = document.createElement("a");
const a5 = document.createElement("a");

div3.className = "row";
div4.className = "col";
a1.textContent = "HOME";
a2.textContent = "LUOGHI";
a3.textContent = "CULTURA";
a4.textContent = "CIBO";
a5.textContent = "CONTATTI";

const array = [a1, a2, a3, a4, a5];
array.forEach(element => {
    element.className = "text-decoration-none text-white p-2";
    element.href = "index.html";
});


div3.style.backgroundColor = "black";   
container.appendChild(div3);
div3.appendChild(div4);
div4.appendChild(a1);
div4.appendChild(a2);
div4.appendChild(a3);
div4.appendChild(a4);
div4.appendChild(a5);



const div5 = document.createElement("div");
const div6 = document.createElement("div");
const div7 = document.createElement("div");
const div8 = document.createElement("div");
const div9 = document.createElement("div");
const div10 = document.createElement("div");
const img = document.createElement("img");
const h3 = document.createElement("h3");
const p2 = document.createElement("p");
    
div5.style.backgroundColor = "#e3dfdf";
div10.style.backgroundColor = "white";
div10.style.borderLeft = "2px solid blue";
div5.className = "row";
div6.className = "col";
div7.className = "row p-2";
div8.className = "col";
div9.className = "row ";
div10.className = "col p-3";

img.className = "img-fluid p-2";
img.src="img.jpg";
h3.textContent = "Trieste tra mare e architettura";
p2.textContent = " Trieste è una città con una identità molto particolare. Il porto, le piazze aperte sul mare e i palazzi eleganti raccontano una storia di commerci, cultura e incontri tra mondi diversi. Passeggiare nel centro significa osservare architetture  monumentali, caffè storici e scorci sul mare."

container.appendChild(div5);
div5.appendChild(div6);
div5.appendChild(div7);
div7.appendChild(div8);
div8.appendChild(img);
div5.appendChild(div9);
div9.appendChild(div10);
div10.appendChild(h3);
div10.appendChild(p2);