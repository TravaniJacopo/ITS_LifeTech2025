const array = ["img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg"];
const btndestra = document.querySelector(".btndestra");
const btnsinistra = document.querySelector(".btnsinistra");
const btnautomatico = document.querySelector(".btnautomatico");
const btnstop = document.querySelector(".btnstop");
const img = document.querySelector(".immagine");
const flag = true;
let index = 0;

btndestra.addEventListener("click", function () {
    index++;    
    if (index >= array.length) 
    {
        index = 0;
    }       
        img.src = array[index];
    
});

btnsinistra.addEventListener("click", function () {
    index--;
    if (index < 0) 
    {
        index = array.length - 1;
    }       
        img.src = array[index];
});

btnautomatico.addEventListener("click", function () {

    btnautomatico.intervalId=setInterval(function () {
        index++;  
        if (index >= array.length)
    {
        index = 0;
    } 
        if (index < 0) 
    {
        index = array.length - 1;
    }       
        img.src = array[index];
    }, 1000);
});

btnstop.addEventListener("click", function () {
    clearInterval(btnautomatico.intervalId);
});