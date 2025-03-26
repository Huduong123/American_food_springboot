
    function toggleDropdown(event) {
        event.preventDefault();
        const menu = document.getElementById('userDropdown');
        menu.classList.toggle('hidden');
    }

    // Ẩn dropdown khi click ra ngoài
    window.addEventListener('click', function (e) {
        const dropdown = document.getElementById('userDropdown');
        const icon = document.querySelector('.user-icon');
        if (!dropdown.contains(e.target) && !icon.contains(e.target)) {
            dropdown.classList.add('hidden');
        }
    });


