let generatedData: {
  labels: string[];
  datasets: { label: string; backgroundColor: string[]; data: number[] }[];
};

export const getDonutChartData = (themes: ColorThemes) => {
  if (generatedData) {
    generatedData.datasets[0].backgroundColor = [
      themes.danger,
      themes.info,
      themes.primary,
    ];
  } else {
    generatedData = {
      labels: ["신호동", "수영", "사직", "양정"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: [
            themes.danger,
            themes.info,
            themes.primary,
            themes.success,
          ],
          data: [127, 267, 34, 17],
        },
      ],
    };
  }

  return generatedData;
};
