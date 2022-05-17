import { useColors } from "vuestic-ui";

function colorToRgba(color: string, a: number) {
  const { shiftHSLAColor } = useColors();

  const transparentColor = shiftHSLAColor(color, { a: -1 });
  return shiftHSLAColor(transparentColor, { a });
}

// 최대 & 최소 횟수
const generateValue = () => {
  return Math.floor(Math.random() * 50);
};

// 수치를 랜덤으로 줌
const generateYLabels = () => {
  const flip = !!Math.floor(Math.random() * 2);
  return flip ? ["월별 횟수"] : ["월별 횟수"];
};

const generateArray = (length: number) => {
  return Array.from(Array(length), generateValue);
};

// 몇개월 보여줄지
const getSize = () => {
  const minSize = 12;
  return Math.max(minSize, new Date().getMonth());
};

let generatedData: GeneratedData;
let firstMonthIndex = 0;

export const getLineChartData = (themes: ColorThemes, firstMonth: number) => {
  const size = getSize();
  const months = [
    "Jan",
    "Feby",
    "Mar",
    "Aprl",
    "May",
    "June",
    "July",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  const yLabels = generateYLabels();

  if (generatedData) {
    generatedData.datasets[0].backgroundColor = colorToRgba(
      themes.primary,
      0.6
    );
    // generatedData.datasets[1].backgroundColor = colorToRgba(themes.info, 0.6);
    // if (firstMonth && firstMonthIndex !== firstMonth) {
    //   generatedData.labels.shift();
    //   generatedData.datasets.forEach((dataset) => {
    //     dataset.data = dataset.data.slice(1);
    //   });
    //   firstMonthIndex = firstMonth;
    // }
  } else {
    generatedData = {
      labels: months.splice(firstMonthIndex, size),
      datasets: [
        {
          label: yLabels[0],
          backgroundColor: colorToRgba(themes.primary, 0.6),
          borderColor: "transparent",
          data: generateArray(size - firstMonthIndex),
        },
        // {
        //   label: yLabels[1],
        //   backgroundColor: colorToRgba(themes.info, 0.6),
        //   borderColor: "transparent",
        //   data: generateArray(size),
        // },
      ],
    };
  }

  return generatedData;
};
