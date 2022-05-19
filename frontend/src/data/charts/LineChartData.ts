import { useColors } from "vuestic-ui";

function colorToRgba(color: string, a: number) {
  const { shiftHSLAColor } = useColors();

  const transparentColor = shiftHSLAColor(color, { a: -1 });
  return shiftHSLAColor(transparentColor, { a });
}

// 최대 & 최소 횟수
const generateValue = (x:number) => {

  console.log(x);
  if(x == 1) {
    return 32;
  }else if(x == 2) {
    return 20;
  }else if(x == 3) {
    return 24;    
  }else if(x == 4) {
    return 32;    
  }else if(x == 5) {
    return 40;    
  }else if(x == 6) {
    return 10;    
  }else if(x == 7) {
    return 34;    
  }else if(x == 8) {
    return 31;    
  }else if(x == 9) {
    return 45;    
  }else if(x == 10) {
    return 48;    
  }else if(x == 11) {
    return 30;    
  }else {
    return 37;    
  }
  
  
  
};

// 수치를 랜덤으로 줌
const generateYLabels = () => {
  const flip = 22;
  return flip;
};

const generateArray = (length: number) => {
  console.log(Array(length))
  return Array.from([1,2,3,4,5,6,7,8,9,10,11,12], x=>generateValue(x));
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
          label: "월별 횟수",
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
