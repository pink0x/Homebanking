tailwind.config = {
    variants: {
      width: ["responsive", "hover", "focus"],
      height: ["responsive", "hover", "focus"]
  },
      theme: {
        extend: {
          colors: {
            azulOscuro: "#051F53",
            verdeClaro: "#79A88C",
            beige: "#E6DDC4",
            beigeFondo: "#FFFCF1",
          }
        }
      },
  
      plugins: [
        function ({ addUtilities }) {
            const newUtilities = {
                ".no-scrollbar::-webkit-scrollbar": {
                    display: "none",
                },
                ".no-scrollbar": {
                    "-ms-overflow-style": "none",
                    "scrollbar-width": "none",
                },
            };
            addUtilities(newUtilities);
        },
    ],
    };