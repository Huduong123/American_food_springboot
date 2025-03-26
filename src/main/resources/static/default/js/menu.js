document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(".filter-btn");
    const items = document.querySelectorAll(".menu-item");

    function filterCategory(category) {
        console.log("Filtering for category:", category);
        items.forEach(item => {
            const itemCategory = item.dataset.category;
            console.log("Item category:", itemCategory);
            if (category === "all" || itemCategory === category) {
                item.style.display = "block";
            } else {
                item.style.display = "none";
            }
        });
    }

    // Hiển thị mặc định "All"
    filterCategory("all");

    buttons.forEach(button => {
        button.addEventListener("click", () => {
            buttons.forEach(btn => btn.classList.remove("active"));
            button.classList.add("active");

            const selectedCategory = button.getAttribute("data-category");
            filterCategory(selectedCategory);
        });
    });
});
