package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likesSum = 500_999_999,
            sharedSum = 1099,
            viewSum = 500_999_999
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCounter.text = preparedTextForIcons(post.likesSum)
            sharesCounter.text = preparedTextForIcons(post.sharedSum)
            watchesCounter.text = preparedTextForIcons(post.viewSum + 1)
            likes.setImageResource(R.drawable.ic_heart)

            likes?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_heart
                )
                if (post.likedByMe) post.likesSum++ else post.likesSum--
                likesCounter.text = preparedTextForIcons(post.likesSum)
            }

            shares?.setOnClickListener {
                shares.setImageResource(R.drawable.ic_baseline_shared_24)
                post.sharedSum++
                sharesCounter.text = preparedTextForIcons(post.sharedSum)
            }
        }
    }

    fun preparedTextForIcons(currentIconSum: Long) = when (currentIconSum) {
        in 1 until 1000 -> currentIconSum.toString()
        in 1_000 until 10_000 -> modificationThousands(currentIconSum, "K")
        in 10_000 until 1_000_000 -> (currentIconSum / 1000).toString() + "K"
        in 1_000_000 until 10_000_000 -> modificationThousands(currentIconSum / 1000, "M")
        in 10_000_000 until 1000_000_000 -> (currentIconSum / 1_000_000).toString() + "M"
        else -> "a lot"
    }

    fun modificationThousands(currentSharedSum: Long, letter: String): String {

        val value = (currentSharedSum / 1000)

        return when (currentSharedSum) {
            in (value * 1000) until (value * 1000) + 100 -> "$value$letter"
            in (value * 1000) + 100 until (value * 1000) + 200 -> "$value.1$letter"
            in (value * 1000) + 200 until (value * 1000) + 300 -> "$value.2$letter"
            in (value * 1000) + 300 until (value * 1000) + 400 -> "$value.3$letter"
            in (value * 1000) + 400 until (value * 1000) + 500 -> "$value.4$letter"
            in (value * 1000) + 500 until (value * 1000) + 600 -> "$value.5$letter"
            in (value * 1000) + 600 until (value * 1000) + 700 -> "$value.6$letter"
            in (value * 1000) + 700 until (value * 1000) + 800 -> "$value.7$letter"
            in (value * 1000) + 800 until (value * 1000) + 900 -> "$value.8$letter"
            in (value * 1000) + 900 until (value * 1000) + 1000 -> "$value.9$letter"
            else -> "error"
        }

    }
}