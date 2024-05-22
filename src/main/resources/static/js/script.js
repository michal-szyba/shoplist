document.addEventListener("DOMContentLoaded", function() {
    let toggleButtons = document.querySelectorAll(".toggle-btn");

    toggleButtons.forEach(button => {
        button.addEventListener("click", function() {
            const productContainer = this.parentNode.nextElementSibling;
            productContainer.classList.toggle("expanded");
            productContainer.classList.toggle("expandable");
            console.log("toggled")
        });
    });

    const checkboxes = document.querySelectorAll('.purchased-checkbox');
    console.log(checkboxes);
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function(){
            const productId = this.getAttribute('data-id');
            const purchased = this.checked;

            fetch('/updatePurchasedStatus', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id:productId,
                    purchased: purchased
                }),
            })
                .then(data => {
                    if(data){
                        console.log("successfully updated \'purchased\' status");
                    } else {
                        console.log("error updating \'purchased\' status");
                    }
                })
                .catch(error => {
                    console.log("error: ", error)
                })
        })
    })
});
