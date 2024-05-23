package com.healthfoodenergyController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.healthfoodenergy.BMIForm;
import com.healthfoodenergy.BMR;
@Controller
public class BMIController {
	
	
	@GetMapping("/bmi")
	public String bmi(){
	//	System.out.print("Hii get");
		return "bmi";
	}
	@GetMapping("/bmr")
	public String bmr(){
	//	System.out.print("Hii get");
		return "bmr";
	}

    @PostMapping("/calculate")
    public String calculateBMI(@ModelAttribute BMIForm bmiForm, Model model) {
        double height = bmiForm.getHeight()/100;
        System.out.print(height);
        double weight = bmiForm.getWeight();
        double bmi = weight / (height * height);
        bmiForm.setBmi(bmi);
        
        if (bmi < 18.5) {
            bmiForm.setResult("Underweight");
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiForm.setResult("Normal weight (Fit)");
        } else if (bmi >= 25 && bmi < 30) {
            bmiForm.setResult("Overweight");
        } else {
            bmiForm.setResult("Obesity");
        }
        System.out.print(bmiForm.getResult());
        model.addAttribute("bmiForm", bmiForm);
        return "bmiResult";
    }
    
  
    @PostMapping("/calculateBMR")
	public String calculateBMR(@ModelAttribute BMR bmr,Model m) {
    
    	
		double height=bmr.getHeight();
        double wieght=bmr.getWieght();
    	int age=bmr.getAge();
    	String gender=bmr.getGender();
        String work=bmr.getWork();
        String genderX="Female";
        String genderY="male";
    	double clBMR = 0;
    	if(gender.equalsIgnoreCase(genderY)) {
    		clBMR=10*wieght+6.25*height-5*age+5;
        System.out.print("BMR for male "+""+clBMR);
    	}
    	if(gender.equalsIgnoreCase(genderX)) {
    		clBMR=10*wieght+6.25*height-5*age+161;
        System.out.print("BMR for Female "+""+clBMR);
    	}
    
        if(work.equalsIgnoreCase("Desktop")){
        clBMR=clBMR+1.2;
        }
       else if(work.equalsIgnoreCase("Light activity")){
        clBMR=clBMR+1.37;
        }
       else if(work.equalsIgnoreCase("6-7workout")){
        clBMR=clBMR+1.55;
        }
       else if(work.equalsIgnoreCase("Desktop")){
        clBMR=clBMR+1.72;
        }
       else if(work.equalsIgnoreCase("Athlete")){
        clBMR=clBMR+1.90;
        }




    	System.out.print("Desktop"+clBMR+1.2);
    	System.out.print("Light activity"+clBMR+1.37);
    	System.out.print("4-5workout"+clBMR+1.55);
    	System.out.print("6-7"+clBMR+1.72);
    	System.out.print("Athlete"+clBMR+1.90);  
    	
    	m.addAttribute("Bmr",clBMR);
    	return "bmrResult";
    }
    
    
}
