document.addEventListener("DOMContentLoaded", function() {
    let toggleButtons = document.querySelectorAll(".toggle-btn");

    toggleButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            let parentDiv = this.parentElement.parentElement;
            let productList = parentDiv.querySelector('.product-list');

            if(productList.classList.contains("expandable")){
                productList.classList.remove("expandable");
                productList.classList.add("expanded");
            } else if(productList.classList.contains("expanded")){
                productList.classList.remove("expanded");
                productList.classList.add("expandable");
            }
            console.log("o skurwysyn");

        });
    });
});
