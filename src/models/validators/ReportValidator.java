package models.validators;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Employee;
import models.Report;

public class ReportValidator {
	public static List<String> validate(Report r) {
		List<String> errors = new ArrayList<String>();

		String title_error = _validateTitle(r.getTitle());
		if (!title_error.equals("")) {
			errors.add(title_error);
		}

		String content_error = _validateContent(r.getContent());
		if (!content_error.equals("")) {
			errors.add(content_error);
		}

		String approval_admin_error = _validateApprovalAadminId(r.getApproval_employee());
		if (!approval_admin_error.equals("")) {
			errors.add(approval_admin_error);
		}

		String start_at_error = _startAt(r.getStart_at());
		if (!start_at_error.equals("")) {
			errors.add(start_at_error);
		}

		String end_at_error = _endAt(r.getEnd_at());
		if (!end_at_error.equals("")) {
			errors.add(end_at_error);
		}

		String start_end_error = checkStartEnd(r.getStart_at(), r.getEnd_at());
		if (!start_end_error.equals("")) {
			errors.add(start_end_error);
		}


		return errors;
	}

	private static String _validateTitle(String title) {
		if (title == null || title.equals("")) {
			return "タイトルを入力してください。";
		}

		return "";
	}

	private static String _validateContent(String content) {
		if (content == null || content.equals("")) {
			return "内容を入力してください。";
		}

		return "";
	}

	private static String _startAt(String start_at) {

		// 時間 00-23、分 00-59 の範囲
		Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
		Matcher m = p.matcher(start_at);
		if (!m.find()) {
			return "出勤時刻を選択してください。";
		}
		return "";

	}

	private static String _endAt(String end_at) {

		// 時間 00-23、分 00-59 の範囲
		Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
		Matcher m = p.matcher(end_at);
		if (!m.find()) {
			return "退勤時刻を選択してください。";
		}
		return "";

	}

	private static String checkStartEnd(String start_at, String end_at) {

		// 正しい時刻形式が入力されていて
		if(_startAt(start_at).equals("") && _endAt(end_at).equals("")){

			Time start_at_time = Time.valueOf(start_at + ":00");
			Time end_at_time = Time.valueOf(end_at + ":00");

			// 出勤時刻が退勤時刻より後の時刻であれば
			if (start_at_time.after(end_at_time)) {
				return "出勤時間と退勤時間の順序が逆になっています。確認してください。";
			}
		}

		return "";

	}

	private static String _validateApprovalAadminId(Employee approval_employee) {
		if (approval_employee == null) {
			return "承認者を選択してください。";
		}

		return "";
	}
}