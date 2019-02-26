package br.com.andrecouto.alodjinha.util

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.AppCompatImageView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import br.com.andrecouto.alodjinha.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("requestFocusListener")
        fun requestFocus(editText: EditText, shouldFocus: Boolean) {
            if (shouldFocus) {
                editText.postDelayed({ editText.requestFocus() }, 100)
            } else {
                editText.postDelayed({ editText.clearFocus() }, 100)
            }
        }

        @JvmStatic
        @BindingAdapter("bindValue")
        fun bindEditTextValue(editText: EditText, field: MutableLiveData<String>) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    field.value = s.toString()
                }
            })
        }


        @JvmStatic
        @BindingAdapter("srcImageUrl")
        fun setImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                val options = RequestOptions().centerCrop()
                GlideApp.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bg_image_not_available)
                        .apply(options)
                        .dontAnimate()
                        .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("matchSrcImageUrl")
        fun setMatchImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                GlideApp.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("srcImageBitmap")
        fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
            bitmap?.let {
                GlideApp.with(imageView.context).load(it)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("srcImageDrawable")
        fun setImageDrawable(imageView: ImageView, drawable: Drawable?) {
            drawable?.let {
                GlideApp.with(imageView.context).load(it)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun setSrcCompatResId(imageView: AppCompatImageView, @DrawableRes drawableResId: Int) {
            imageView.setImageResource(drawableResId)
        }

        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun setSrcCompatDrawable(imageView: AppCompatImageView, drawable: Drawable) {
            imageView.setImageDrawable(drawable)
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundResId(view: View, @DrawableRes drawableResId: Int) {
            view.setBackgroundResource(drawableResId)
        }
    }
}