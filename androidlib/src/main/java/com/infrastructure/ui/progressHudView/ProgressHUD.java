package com.infrastructure.ui.progressHudView;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixunfan.androidlib.R;

public class ProgressHUD extends Dialog {
	public ProgressHUD(Context context) {
		super(context);
	}

	public ProgressHUD(Context context, int theme) {
		super(context, theme);
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);

		ObjectAnimator oa = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 360f).setDuration(2000);
		oa.setRepeatCount(100);
		oa.setInterpolator(new LinearInterpolator());
		oa.start();

		ObjectAnimator oa2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 0.6f, 1).setDuration(2000);
		oa2.setRepeatCount(100);
		oa2.setInterpolator(new LinearInterpolator());
		oa2.start();

		ObjectAnimator oa3 = ObjectAnimator.ofFloat(imageView, "scaleY", 1, 0.6f, 1).setDuration(2000);
		oa3.setRepeatCount(100);
		oa3.setInterpolator(new LinearInterpolator());
		oa3.start();

		// AnimationDrawable spinner = (AnimationDrawable)
		// imageView.getBackground();
		// spinner.start();
	}

	public void setMessage(CharSequence message) {
		if (message != null && message.length() > 0) {
			findViewById(R.id.message).setVisibility(View.VISIBLE);
			TextView txt = (TextView) findViewById(R.id.message);
			txt.setText(message);
			txt.invalidate();
		}
	}

	public static ProgressHUD show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
		ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
		dialog.setTitle("");
		dialog.setContentView(R.layout.widgt_progress_hud);
		if (message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);
		} else {
			TextView txt = (TextView) dialog.findViewById(R.id.message);
			txt.setText(message);
		}
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.dimAmount = 0.2f;
		dialog.getWindow().setAttributes(lp);
		if (context == null)
			return null;
		dialog.show();
		return dialog;
	}
}
