package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Customer;

public class CustomerValidator {
	public static List<String> validate(Customer c) {
		List<String> errors = new ArrayList<String>();

		String company_error = _validateCompany(c.getCompany());
		if (!company_error.equals("")) {
			errors.add(company_error);
		}

		String position_error = _validatePosition(c.getPosition());
		if (!position_error.equals("")) {
			errors.add(position_error);
		}

		String name_error = _validateName(c.getName());
		if (!name_error.equals("")) {
			errors.add(name_error);
		}

		String email_error = _validateEmail(c.getEmail());
		if (!email_error.equals("")) {
			errors.add(email_error);
		}

		String tel_error = _validateTel(c.getTel());
		if (!tel_error.equals("")) {
			errors.add(tel_error);
		}

		return errors;
	}

	// 会社名
	private static String _validateCompany(String company) {
		// 必須入力チェック
		if (company == null || company.equals("")) {
			return "会社名を入力してください。";
		}

		return "";
	}

	// 役職名の必須入力チェック
	private static String _validatePosition(String position) {
		if (position == null || position.equals("")) {
			return "役職名を入力してください。";
		}

		return "";
	}

	// 顧客名の必須入力チェック
	private static String _validateName(String name) {
		if (name == null || name.equals("")) {
			return "顧客名を入力してください。";
		}

		return "";
	}

	// 電話番号の必須入力チェック
	private static String _validateTel(String tel) {
		if (tel == null || tel.equals("")) {
			return "電話番号を入力してください。";
		}

		return "";
	}

	// メールアドレスの必須入力チェック
	private static String _validateEmail(String email) {
		if (email == null || email.equals("")) {
			return "メールアドレスを入力してください。";
		}

		return "";
	}

}
