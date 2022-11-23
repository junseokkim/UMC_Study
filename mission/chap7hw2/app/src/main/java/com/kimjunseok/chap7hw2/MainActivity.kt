package com.kimjunseok.chap7hw2

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.SeekBar
import com.kimjunseok.chap7hw2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mp3List: ArrayList<Music>
    private var mPlayer: MediaPlayer? = MediaPlayer()
    var nowPlayed: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var handler = Handler(mainLooper)
        var timeFormat = SimpleDateFormat("mm:ss")
        setContentView(binding.root)

        mp3List = ArrayList()
        mp3List.apply {
            add(Music("Energy","Bensound"))
            add(Music("Mighty Love","Joakim Karud"))
            add(Music("Escape","Sam Ourt"))
        }
        var playList: ArrayList<Int> = ArrayList()
        playList.apply {
            add(R.raw.bensound_energy)
            add(R.raw.joakimkarud_mightylove)
            add(R.raw.samourt_escape)
        }
        val assetManager = resources.assets
        val inputStream= assetManager.open("bensound_energy.mp3")


        binding.apply {
            btnStart.setOnClickListener {
                btnPause.visibility = View.VISIBLE
                btnStart.visibility = View.GONE
                mPlayer?.setDataSource(inputStream)
                seekbarMusic.max = mPlayer!!.duration
                mPlayer?.start()
                Thread() {
                    handler.post {
                        totalTime.setText(timeFormat.format(mPlayer?.duration))
                    }
                    while(mPlayer!!.isPlaying) {
                        handler.post {
                            seekbarMusic.progress = mPlayer!!.currentPosition
                            presentTime.setText(timeFormat.format(mPlayer?.currentPosition))
                        }

                        Thread.sleep(200)
                    }
                }.start()
            }
            btnPause.setOnClickListener {
                btnPause.visibility = View.GONE
                btnStart.visibility = View.VISIBLE
                if(mPlayer!!.isPlaying == true) {
                    mPlayer?.pause()
                }
            }
            btnPrevious.setOnClickListener {
                if(seekbarMusic.progress > 2000) {
                    mPlayer?.seekTo(0)
                    seekbarMusic.progress = 0
                    presentTime.setText(timeFormat.format(mPlayer?.currentPosition))
                    btnPause.visibility = View.VISIBLE
                    btnStart.visibility = View.GONE
                }
                else {
                    mPlayer?.stop()
                    if(nowPlayed == 0) nowPlayed = mp3List.size - 1
                    else nowPlayed--
                    mPlayer = MediaPlayer.create(this@MainActivity , playList[nowPlayed])
                    btnPause.visibility = View.VISIBLE
                    btnStart.visibility = View.GONE
                    mPlayer?.start()
                }
            }
            btnNext.setOnClickListener {
                mPlayer?.stop()
                if(nowPlayed == mp3List.size -1 ) nowPlayed = 0
                else nowPlayed++
                mPlayer = MediaPlayer.create(this@MainActivity , playList[nowPlayed])
                btnPause.visibility = View.VISIBLE
                btnStart.visibility = View.GONE
                mPlayer?.start()
                // 재생을 멈추고 뒤로가기나 다음을 클릭 시 present가 변화하지않고 노래만 재생 즉 thread가 작동이 멈춤
                mPlayer?.seekTo(0)
                seekbarMusic.progress = 0
                presentTime.setText(timeFormat.format(mPlayer?.currentPosition))
            }
            seekbarMusic.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        mPlayer?.pause()
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        mPlayer!!.seekTo(seekbarMusic.progress)
                        // 이게 없으면 현재 시간이 최신화가 되지 않음
                        Thread() {
                            while(mPlayer!!.isPlaying) {
                                handler.post {
                                    btnPause.visibility = View.VISIBLE
                                    btnStart.visibility = View.GONE
                                    presentTime.setText(timeFormat.format(mPlayer?.currentPosition))
                                }
                                Thread.sleep(200)
                            }
                        }.start()
                        mPlayer?.start()
                    }
                })

            /*
            mPlayer?.setOnSeekCompleteListener {
                mPlayer?.stop()
                if(nowPlayed == mp3List.size -1 ) nowPlayed = 0
                else nowPlayed++
                mPlayer = MediaPlayer.create(this@MainActivity , playList[nowPlayed])
                mPlayer?.prepare()
                mPlayer?.start()
            }

             */
        }

        mPlayer?.setOnCompletionListener {
            if(nowPlayed == mp3List.size -1 ) nowPlayed = 0
            else nowPlayed++
            mPlayer = MediaPlayer.create(this@MainActivity , playList[nowPlayed])
            mPlayer?.prepare()
            mPlayer?.start()
        }
    }

    override fun onStop() {
        mPlayer?.release()
        mPlayer = null
        super.onStop()
    }
}