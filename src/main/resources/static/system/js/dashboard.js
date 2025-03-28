
    document.addEventListener("DOMContentLoaded", function () {


        const lineChart = new Chart(document.getElementById('lineChart'), {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    label: 'Earnings',
                    data: [10000, 15000, 20000, 25000, 30000, 35000, 40000],
                    borderColor: '#198754',
                    tension: 0.4
                }]
            }
        });

        const doughnutChart = new Chart(document.getElementById('doughnutChart'), {
            type: 'doughnut',
            data: {
                labels: ['Direct', 'Referral', 'Social'],
                datasets: [{
                    data: [55, 30, 15],
                    backgroundColor: ['#198754', '#20c997', '#d4edda']
                }]
            }
        });
    });
